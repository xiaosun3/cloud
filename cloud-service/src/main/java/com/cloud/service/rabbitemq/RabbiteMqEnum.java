package com.cloud.service.rabbitemq;

import lombok.Data;

/**
 * Created by sunhaidi on 2019-06-06.
 */
public enum RabbiteMqEnum {

    queue_test("queue_test"),
    topic_test("topic_test");


    public String value;

    RabbiteMqEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
