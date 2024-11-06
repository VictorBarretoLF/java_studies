package com.course.kafka.broker.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaConnectSchema {

    public enum Type {
        struct, string, int32
    }

    private String type;
    private boolean optional;
    private String field;
    private List<KafkaConnectSchema> fields;

    public KafkaConnectSchema() {

    }

    public KafkaConnectSchema(String type, boolean optional, String field, List<KafkaConnectSchema> fields) {
        this.type = type;
        this.optional = optional;
        this.field = field;
        this.fields = fields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<KafkaConnectSchema> getFields() {
        return fields;
    }

    public void setFields(List<KafkaConnectSchema> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "KafkaConnectSchema [type=" + type + ", optional=" + optional + ", field=" + field + ", fields=" + fields
                + "]";
    }

}
