package com.example.kafkatopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic kafkaSum()
    {
        return TopicBuilder.name(
                "kafkaSum"
        ).build();
    }

    @Bean
    public NewTopic sumResult()
    {
        return TopicBuilder.name(
                "sumResult"
        ).build();
    }
}
