package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

// @Service
public class FixedRateAIConsumer {

    // Using Spring, consume from Kafka topic "t-fixedrate" and display the message.
    @KafkaListener(topics = "t-fixedrate")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
    }

}
