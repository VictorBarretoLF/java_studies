package com.appsdeveloperblog.ws.products.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-events-topic") // Sets the name of the topic
                .partitions(3) // Number of partitions
                .replicas(1) // Number of replicas for each partition
                .configs(Map.of("min.insync.replicas", "1")) // Min number required replicas to acknowledge a write
                .build();
    }

}
