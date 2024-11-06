package com.course.kafka.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.course.avro.data.EmployeeBackward;
import com.course.kafka.broker.producer.EmployeeBackwardProducer;

import net.datafaker.Faker;

// @Service
public class EmployeeBackwardScheduler {

    @Autowired
    private EmployeeBackwardProducer producer;

    private Faker faker = new Faker();

    @Scheduled(fixedRate = 1000)
    public void publishSchedule() {
        var data = EmployeeBackward.newBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .build();

        producer.send(data);
    }

}
