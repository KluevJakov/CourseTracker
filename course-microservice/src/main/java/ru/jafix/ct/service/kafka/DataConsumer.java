package ru.jafix.ct.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.dto.UserDto;

@Slf4j
@Service
public class DataConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = {"${topics.user}"}, groupId = "${topics.groupId}")
    public void consumeData(String value) {
        try {
            UserDto userFromBroker = objectMapper.readValue(value, UserDto.class);
            System.out.println(userFromBroker);
        } catch (JsonProcessingException e) {
            log.error("Не распарсить данные из kafka {}", e.getMessage());
        }
    }
}
