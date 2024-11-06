package com.course.kafka;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.config.KafkaConfig;
import com.course.kafka.producer.BinaryAsBase64Producer;

@SpringBootApplication
public class KafkaOtherDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaOtherDemoApplication.class, args);
    }

    @Autowired
    private BinaryAsBase64Producer producer;

    @Override
    public void run(String... args) throws Exception {
        var file = new File("src/main/resources/indonesia-prambanan-temple.jpg");

        producer.send(KafkaConfig.TOPIC_DEMO_BINARY, file);
    }

}
