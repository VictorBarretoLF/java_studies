package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

// @Service
public class CounterProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessages(int number) {
        for (int i = 0; i < number; i++) {
            String message = "Data " + i;
            kafkaTemplate.send("t-counter", message);
        }
    }

}