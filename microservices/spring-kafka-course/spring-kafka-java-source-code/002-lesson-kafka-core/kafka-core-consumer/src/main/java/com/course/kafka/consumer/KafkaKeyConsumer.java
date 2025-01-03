package com.course.kafka.consumer;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

// @Service
public class KafkaKeyConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaKeyConsumer.class);

    @KafkaListener(topics = "t-multi-partitions", concurrency = "4")
    public void consume(ConsumerRecord<String, String> record) throws InterruptedException {
        LOG.info("Key : {}, Partition : {}, Message : {}", record.key(), record.partition(), record.value());
        TimeUnit.SECONDS.sleep(1);
    }

}
