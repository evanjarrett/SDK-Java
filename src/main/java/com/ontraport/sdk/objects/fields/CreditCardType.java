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

    private final int _value;
    private final String _name;
    private static Map<Integer, CreditCardType> _map = new HashMap<>();

    CreditCardType(int value, String name) {
        _value = value;
        _name = name;
    }

    static {
        for (CreditCardType cct : CreditCardType.values()) {
            _map.put(cct._value, cct);
        }
    }

    public static CreditCardType valueOf(int cct) {
        return _map.get(cct);
    }

    public static String getNameFromValue(int value) throws InvalidValueException {
        if (!_map.containsKey(value)) {
            throw new InvalidValueException();
        }
        return _map.get(value).getName();
    }

    public int getValue() {
        return _value;
    }

    public String getName() {
        return _name;
    }
}
