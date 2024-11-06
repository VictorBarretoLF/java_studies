package com.course.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

// @Service
public class FixedRateProducer {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int i = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        LOG.info("i is {}", i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate " + i);
    }

}
