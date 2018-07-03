package com.ontraport.sdk.objects.fields;

import java.util.HashMap;
import java.util.Map;

public enum FieldType {

    CHECK("check"),
    COUNTRY("country"),
    FULLDATE("fulldate"),
    LIST("list"),
    LONGTEXT("longtext"),
    NUMERIC("numeric"),
    PRICE("price"),
    PHONE("phone"),
    STATE("state"),
    DROP("drop"),
    TEXT("text"),
    EMAIL("email"),
    SMS("sms"),
    ADDRESS("address");

    private final String _type;
    private static Map<String, FieldType> _map = new HashMap<>();

    FieldType(String type) {
        _type = type;
    }

    static {
        for (FieldType ft : FieldType.values()) {
            _map.put(ft._type, ft);
        }
    }

    public static FieldType typeOf(String ft) {
        return _map.get(ft);
    }

    public String getType() {
        return _type;
    }
}
