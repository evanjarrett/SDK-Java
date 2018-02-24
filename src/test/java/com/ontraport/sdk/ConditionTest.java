package com.ontraport.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ontraport.sdk.criteria.Condition;
import com.ontraport.sdk.criteria.Operator;
import junit.framework.TestCase;

import java.util.Arrays;

public class ConditionTest extends TestCase {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public void testSingleValue() {
        Condition cond = new Condition<>(
                "email",
                Operator.EQUALS,
                "test@test.com"
        );
        assertEquals("{\"field\":{\"field\":\"email\"},\"op\":\"=\",\"value\":{\"value\":\"test@test.com\"}}",
                gson.toJson(cond));
    }

    public void testListValue() {
        Condition cond = new Condition<>(
                "email",
                Operator.IN,
                Arrays.asList("first@test.com", "second@test.com", "third@test.com")
        );
        assertEquals("{\"field\":{\"field\":\"email\"},\"op\":\"IN\",\"value\":{\"list\":[{\"value\":\"first@test.com\"},{\"value\":\"second@test.com\"},{\"value\":\"third@test.com\"}]}}",
                gson.toJson(cond));
    }
}
