package com.course.kafka.scheduler;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.AddressMessage;
import com.course.kafka.broker.message.PersonMessage;
import com.course.kafka.broker.producer.PersonProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.datafaker.Faker;

@Service
public class PersonAddressScheduler {

    private Faker faker = new Faker();

    @Autowired
    private PersonProducer producer;

    private AddressMessage fakeAddress() {
        var address = new AddressMessage();

        address.setAddress(faker.address().fullAddress());
        address.setPostalCode(faker.address().zipCode());
        address.setCity(faker.address().city());
        address.setAddressId(faker.number().numberBetween(1, 90000));

        return address;
    }

    private PersonMessage fakePerson() {
        var person = new PersonMessage();
        var addresses = new ArrayList<AddressMessage>();

        person.setPersonId(faker.number().numberBetween(1, 90000));
        person.setAddresses(addresses);
        person.setEmail(faker.internet().emailAddress());
        person.setFullName(faker.name().fullName());

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(4); i++) {
            addresses.add(fakeAddress());
        }

        return person;
    }

    @Scheduled(fixedRate = 5000)
    public void publishDummyPerson() throws JsonProcessingException {
        producer.publish(fakePerson());
    }

}
