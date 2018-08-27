package com.ontraport.sdk.models.rules;

public class RulePart<T extends RuleType> {
    private T _type;
    private String _value;
    private Operator _operator;

    RulePart(T type, String value) {
        this(type, value, null);
    }

    RulePart(T type, String value, Operator operator) {
        _type = type;
        _value = value;
        _operator = operator;
    }

    public T getType() {
        return _type;
    }

    public Operator getOperator() {
        return _operator;
    }

    public String getValue() {
        return _value;
    }
}
