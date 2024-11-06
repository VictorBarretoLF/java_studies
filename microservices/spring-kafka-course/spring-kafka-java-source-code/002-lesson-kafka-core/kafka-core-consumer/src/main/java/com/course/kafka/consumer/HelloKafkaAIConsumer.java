package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

// @Component
public class HelloKafkaAIConsumer {

    @KafkaListener(topics = "t-hello")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
