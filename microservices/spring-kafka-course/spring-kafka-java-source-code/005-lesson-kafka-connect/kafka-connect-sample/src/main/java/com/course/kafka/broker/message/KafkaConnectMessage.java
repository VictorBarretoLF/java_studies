package com.course.kafka.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaConnectMessage<T> {

    private KafkaConnectSchema schema;
    private T payload;

    public KafkaConnectSchema getSchema() {
        return schema;
    }

    public void setSchema(KafkaConnectSchema schema) {
        this.schema = schema;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "KafkaConnectMessage [schema=" + schema + ", payload=" + payload + "]";
    }

}
