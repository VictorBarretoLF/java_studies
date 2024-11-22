package com.victorbarreto.kafka.core.producer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateProducer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        this.counter++;
        logger.info("Sending fixed rate message: " + this.counter);
        kafkaTemplate.send("t-fixedrate", "Fixed rate message: " + this.counter);
    }

}
