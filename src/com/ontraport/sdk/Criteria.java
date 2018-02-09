package com.ontraport.sdk;

public class Criteria {

    protected String[] _condition;

    public Criteria(String field, String operator, String value) {
        _condition = buildCondition(field, operator, value);
    }

    public String fromArray() {
        JsonObject obj = Json.createObjectBuilder().build();
        return "";
    }

    private String[] buildCondition(String field, String operator, String value) {

    }
}
