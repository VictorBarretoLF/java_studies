package com.victorbarreto.kafka.core.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorbarreto.kafka.core.entity.Commodity;
import com.victorbarreto.kafka.core.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

//@Service
public class CommodityProducer {

    private static final Logger logger = LoggerFactory.getLogger(CommodityProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Commodity commodity) {
        try {
            var json = objectMapper.writeValueAsString(commodity);
            kafkaTemplate.send("t-commodity", commodity.getName(), json);
            logger.info("Sent message to t-commodity: {}", json);
        } catch (Exception e) {
            logger.error("Failed to send message to t-commodity for commodity: {}", commodity, e);
        }
    }

}
