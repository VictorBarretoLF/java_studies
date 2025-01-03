package com.course.kafka.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.avro.data.EmployeeForward;

// @Service
public class EmployeeForwardConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeForwardConsumer.class);

    @KafkaListener(topics = "sc-employee-forward")
    public void listen(ConsumerRecord<String, EmployeeForward> record) {
        LOG.info("{} : {}", record.key(), record.value());
    }

}
