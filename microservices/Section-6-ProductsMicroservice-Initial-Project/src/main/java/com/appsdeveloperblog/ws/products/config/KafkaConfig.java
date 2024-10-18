package com.appsdeveloperblog.ws.products.config;

import com.appsdeveloperblog.ws.products.event.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String KeySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String ValueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeoutMs;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String lingerMs;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeoutMs;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KeySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ValueSerializer);
        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs);
        config.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs);

        return config;
    }

    @Bean
    public ProducerFactory<String, ProductCreatedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(this.producerConfigs());
    }

    @Bean
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<String, ProductCreatedEvent>(this.producerFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-events-topic") // Sets the name of the topic
                .partitions(3) // Number of partitions
                .replicas(1) // Number of replicas for each partition
                .configs(Map.of("min.insync.replicas", "1")) // Min number required replicas to acknowledge a write
                .build();
    }

}
