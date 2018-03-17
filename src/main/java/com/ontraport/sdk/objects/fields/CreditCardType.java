package com.ontraport.sdk.objects.fields;

import com.ontraport.sdk.exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public enum CreditCardType {
    NONE(0, "none"),
    VISA(1, "Visa"),
    MASTERCARD(2, "Mastercard"),
    AMERICAN_EXPRESS(3, "American Express"),
    DISCOVER(4, "Discover"),
    PAYPAL(5, "Paypal");

    private final int value;
    private final String name;
    private static Map<Integer, CreditCardType> map = new HashMap<>();

    CreditCardType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    static {
        for (CreditCardType cct : CreditCardType.values()) {
            map.put(cct.value, cct);
        }
    }

    public static CreditCardType valueOf(int cct) {
        return map.get(cct);
    }

    public static String getNameFromValue(int value) throws InvalidValueException {
        if (!map.containsKey(value)) {
            throw new InvalidValueException();
        }
        return map.get(value).getName();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
