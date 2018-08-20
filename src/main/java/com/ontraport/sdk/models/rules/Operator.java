package com.ontraport.sdk.models.rules;


import java.util.HashMap;
import java.util.Map;

public enum Operator {

    AND("|"),
    OR(";");

    private final String _operator;
    private static Map<String, Operator> _map = new HashMap<>();

    Operator(String rule) {
        _operator = rule;
    }

    static {
        for (Operator action : Operator.values()) {
            _map.put(action._operator, action);
        }
    }

    public static Operator fromOperator(String operator) {
        return _map.get(operator);
    }

    public String toString() {
        return _operator;
    }

}
