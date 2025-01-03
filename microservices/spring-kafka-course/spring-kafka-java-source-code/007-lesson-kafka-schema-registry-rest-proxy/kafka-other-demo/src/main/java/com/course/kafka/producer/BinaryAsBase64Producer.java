package com.course.kafka.producer;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BinaryAsBase64Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, File file) throws IOException {
        var fileBytes = FileUtils.readFileToByteArray(file);
        var fileBase64 = Base64.getEncoder().encodeToString(fileBytes);

        kafkaTemplate.send(topic, fileBase64);
    }

}
