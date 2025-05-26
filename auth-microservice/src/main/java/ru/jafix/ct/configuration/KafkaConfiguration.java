package ru.jafix.ct.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@EnableKafka
@Configuration
public class KafkaConfiguration {

}
