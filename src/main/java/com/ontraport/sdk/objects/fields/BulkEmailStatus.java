package com.ontraport.sdk.objects.fields;

import com.ontraport.sdk.exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public enum BulkEmailStatus {
    OPT_OUT(0, "Opted-out"),
    OPT_IN(1, "Opted-in"),
    DOUBLE_OPT_IN(2, "Double opt-in"),
    HARD_BOUNCE(-2, "Hard bounce"),
    UNCONFIRMED(-3, "Unconfirmed"),
    UNDER_REVIEW(-5, "Under review");

    private final int _value;
    private final String _name;
    private static Map<Integer, BulkEmailStatus> _map = new HashMap<>();

    BulkEmailStatus(int value, String name) {
        _value = value;
        _name = name;
    }

    static {
        for (BulkEmailStatus bes : BulkEmailStatus.values()) {
            _map.put(bes._value, bes);
        }
    }

    public static BulkEmailStatus valueOf(int bes) {
        return _map.get(bes);
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
