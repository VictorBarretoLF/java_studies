package com.victorbarreto.kafka.core.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRate2Producer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        this.counter++;
        logger.info("Sending message to t-fixedrate2: " + this.counter);
        kafkaTemplate.send("t-fixedrate2", "Fixed rate message: " + this.counter);
    }

}
