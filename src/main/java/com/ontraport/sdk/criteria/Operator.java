package com.ontraport.sdk.criteria;

public enum Operator {
    EQUALS("="),
    NOT_EQUALS("<>"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATOR_OR_EQUAL(">="),
    LESS_OR_EQUAL("<="),
    IN("IN"),
    NOT_IN("NOT IN"),
    IS("IS"),
    LIKE("LIKE");

    private final String _op;

    Operator(final String op) {
        _op = op;
    }

    public String toString() {
        return _op;
    }
}
