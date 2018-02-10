package com.ontraport.sdk.critera;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ontraport.sdk.exceptions.ArrayOperatorException;

import java.util.ArrayList;
import java.util.List;

public class Criteria<T> {

    private String builtCondition;

    private ArrayList<Condition> _condition = new ArrayList<>();

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();


    public Criteria(String field, Operator operator, T value) throws ArrayOperatorException {
        _condition.add(buildCondition(field, operator, value));
    }

    public String toString() {
        return builtCondition;
    }

    public void andCondition(String field, Operator operator, T value) throws ArrayOperatorException {
        conditionToString("AND", buildCondition(field, operator, value));
    }


    public void orCondition(String field, Operator operator, T value) throws ArrayOperatorException {
        conditionToString("OR", buildCondition(field, operator, value));
    }

    private void conditionToString(String append, Condition cond) {
        builtCondition += ", \"" + append + "\", ";
        _condition.add(cond);
        builtCondition += gson.toJson(cond);
    }

    private void validateCondition(Operator operator, T value) throws ArrayOperatorException {
        if (value instanceof List && operator != Operator.IN) {
            throw new ArrayOperatorException();
        }
    }

    private Condition buildCondition(String field, Operator operator, T value) throws ArrayOperatorException {
        validateCondition(operator, value);
        return new Condition<>(field, operator, value);
    }
}
