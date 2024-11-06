package com.course.kafka.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.course.avro.data.EmployeeForward;
import com.course.kafka.broker.producer.EmployeeForwardProducer;

import net.datafaker.Faker;

// @Service
public class EmployeeForwardScheduler {

    @Autowired
    private EmployeeForwardProducer producer;

    private Faker faker = new Faker();

    @Scheduled(fixedRate = 1000)
    public void publishSchedule() {
        var data = EmployeeForward.newBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().safeEmailAddress())
                .build();

        producer.send(data);
    }

}
