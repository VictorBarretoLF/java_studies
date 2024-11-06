package com.course.kafka.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.course.avro.data.PersonAddressPostgresql;
import com.course.kafka.broker.producer.PersonAddressPostgresqlProducer;

import net.datafaker.Faker;

// @Service
public class PersonAddressScheduler {

    private Faker faker = new Faker();

    @Autowired
    private PersonAddressPostgresqlProducer producer;

    private PersonAddressPostgresql fakePersonAddress() {
        var result = new PersonAddressPostgresql();

        result.setEmail(faker.internet().emailAddress());
        result.setFullName(faker.name().fullName());
        result.setAddress(faker.address().streetAddress());
        result.setCity(faker.address().city());
        result.setPostalCode(faker.address().zipCode());

        return result;
    }

    @Scheduled(fixedRate = 3000)
    public void generatePersonAddress() {
        var personAddress = fakePersonAddress();
        producer.publish(personAddress);
    }

}
