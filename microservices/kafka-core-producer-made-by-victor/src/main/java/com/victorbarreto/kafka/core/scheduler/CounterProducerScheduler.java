package com.victorbarreto.kafka.core.scheduler;

import com.victorbarreto.kafka.core.producer.CounterProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CounterProducerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CounterProducerScheduler.class);

    @Autowired
    private CounterProducer counterProducer;

    @Scheduled(fixedRate = 10000)
    public void produce() {
        try {
            int numberOfMessages = 100;
            counterProducer.sendMessages(numberOfMessages);
            logger.info("Produced {} messages", numberOfMessages);
        } catch (Exception e) {
            logger.error("Failed to produce messages", e);
        }
    }

}
