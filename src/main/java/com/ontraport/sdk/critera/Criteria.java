package com.ontraport.sdk.critera;

import com.google.gson.Gson;

public class Criteria<T> {

    protected Condition _condition;

    public Criteria(String field, Operator operator, T value) {
        _condition = buildCondition(field, operator, value);
    }

    public String fromArray() {
        Gson gson = new Gson();
        return gson.toJson(_condition);
    }

    private Condition buildCondition(String field, Operator operator, T value) {
        return new Condition<>(field, operator, value);
    }
}
