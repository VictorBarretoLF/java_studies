package com.course.kafka.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;

// @Service
public class CounterConsumer {

    @KafkaListener(topics = "t-counter", groupId = "counter-group-fast")
    public void consumeFast(String message) {
        System.out.println("Fast: " + message);
    }

    @KafkaListener(topics = "t-counter", groupId = "counter-group-slow")
    public void consumeSlow(String message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Slow: " + message);
    }

}
