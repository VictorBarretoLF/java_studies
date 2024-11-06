package com.course.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

// @Service
public class FixedRateAIProducer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateAIProducer.class);
    private static final String TOPIC = "t-fixedrate";
    private int counter = 0;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        counter++;
        String message = "Message " + counter;
        kafkaTemplate.send(TOPIC, message);
        logger.info("Sent message: " + message + " to topic: " + TOPIC);
    }
}