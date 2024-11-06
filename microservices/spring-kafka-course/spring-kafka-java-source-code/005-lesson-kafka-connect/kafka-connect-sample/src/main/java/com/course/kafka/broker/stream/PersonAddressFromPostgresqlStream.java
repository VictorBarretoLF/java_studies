package com.course.kafka.broker.stream;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonAddressFromPostgresqlStream {

    @Autowired
    void kstreamPersonAddressFromPostgresql(StreamsBuilder builder) {
        var sourceStream = builder.stream("t-person-address-postgresql");

        sourceStream.to("t-person-address-target");
    }

}
