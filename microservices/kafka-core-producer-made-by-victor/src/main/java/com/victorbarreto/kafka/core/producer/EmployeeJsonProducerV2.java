package com.victorbarreto.kafka.core.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class EmployeeJsonProducerV2 {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeJsonProducerV2.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final Random random = new Random();

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        Employee employee = new Employee(
                "E" + random.nextInt(1000),
                "John Doe" + random.nextInt(100),
                LocalDate.of(1990 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28))
        );

        // convert the employee to JSON string and publish it to topic t-employee
        try {
            var json = objectMapper.writeValueAsString(employee);
            kafkaTemplate.send("t-employee-v2", json);
            logger.info("Sent message to t-employee-2-v2: {}", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
