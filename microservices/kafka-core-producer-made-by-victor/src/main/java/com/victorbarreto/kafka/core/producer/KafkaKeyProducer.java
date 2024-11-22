package com.victorbarreto.kafka.core.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class KafkaKeyProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaKeyProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        this.counter++;
        String key = "key" + this.counter;
        String message = "Message " + this.counter;
        logger.info("Sending message to t-multi-partitions: key={}, message={}", key, message);
        ProducerRecord<String, String> record = new ProducerRecord<>("t-multi-partitions", key, message);
        kafkaTemplate.send(record);
    }

}
