package ru.jafix.ct.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;

import java.util.UUID;

@Slf4j
@Service
public class DataProducer {

    @Value("${topics.user}")
    private String TOPIC_NAME;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<UUID, String> kafkaTemplate;

    public void publishMessage(User user) {
        try {
            String stringifyUser = objectMapper.writeValueAsString(user);
            kafkaTemplate.send(TOPIC_NAME, UUID.randomUUID(), stringifyUser);
        } catch (JsonProcessingException e) {
            log.error("Не удалось отправить данные в kafka {}", user.getId());
        }
    }
}
