package com.appsdeveloperblog.ws.products.config;

import com.appsdeveloperblog.ws.core.ProductCreatedEvent;
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

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private Boolean enableIdempotence;

    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private Integer inFlightRequestsPerConnection;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.KeySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.ValueSerializer);
        config.put(ProducerConfig.ACKS_CONFIG, this.acks);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, this.deliveryTimeoutMs);
        config.put(ProducerConfig.LINGER_MS_CONFIG, this.lingerMs);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.requestTimeoutMs);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, this.enableIdempotence);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, this.inFlightRequestsPerConnection);
        //config.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);

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
