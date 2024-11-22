package com.victorbarreto.kafka.core.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CounterConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CounterConsumer.class);

    @KafkaListener(topics = "t-counter", groupId = "counter-group-fast")
    public void consumeFast(String message) {
        logger.info("Fast consumer received message: {}", message);
    }

    @KafkaListener(topics = "t-counter", groupId = "counter-group-slow")
    public void consumeSlow(String message) throws InterruptedException {
        logger.info("Slow consumer received message: {}", message);
        TimeUnit.SECONDS.sleep(3);
        logger.info("Slow consumer finished processing message: {}", message);
    }

}
