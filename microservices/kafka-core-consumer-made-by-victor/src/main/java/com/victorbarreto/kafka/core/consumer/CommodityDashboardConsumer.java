package com.victorbarreto.kafka.core.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorbarreto.kafka.core.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class CommodityDashboardConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory.getLogger(CommodityDashboardConsumer.class);

    @KafkaListener(topics = "t-commodity", groupId = "consumer-group-dashboard")
    public void listen(String message) {
        try {
            Commodity commodity = objectMapper.readValue(message, Commodity.class);

            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5, 10));

            LOG.info("Dashboard consumer: {}", commodity);
        } catch (Exception e) {
            LOG.error("Error processing message", e);
        }
    }

}