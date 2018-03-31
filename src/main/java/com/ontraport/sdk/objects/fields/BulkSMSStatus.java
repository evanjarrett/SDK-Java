package com.ontraport.sdk.objects.fields;

import com.ontraport.sdk.exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public enum BulkSMSStatus {
    OPT_OUT(0, "Opted-out"),
    OPT_IN(1, "Opted-in"),
    DOUBLE_OPT_IN(2, "Double opt-in"),
    HARD_BOUNCE(-2, "Hard bounce");

    private final int _value;
    private final String _name;
    private static Map<Integer, BulkSMSStatus> _map = new HashMap<>();

    BulkSMSStatus(int value, String name) {
        this._value = value;
        this._name = name;
    }

    static {
        for (BulkSMSStatus bss : BulkSMSStatus.values()) {
            _map.put(bss._value, bss);
        }
    }

    public static BulkSMSStatus valueOf(int bss) {
        return _map.get(bss);
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
