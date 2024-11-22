package com.victorbarreto.kafka.core.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumerV2 {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateConsumerV2.class);

    @KafkaListener(topics = "t-fixedrate2")
    public void consume(String message) {
        logger.info("Consumed message from topic 't-fixedrate2': {}", message);
    }

}
