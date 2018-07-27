package com.ontraport.sdk.objects.fields;

import java.util.HashMap;
import java.util.Map;

public enum FieldType {

    ADDRESS("address"),
    CHECK("check"),
    COUNTRY("country"),
    DROP("drop"),
    EMAIL("email"),
    FULLDATE("fulldate"),
    LIST("list"),
    LONGTEXT("longtext"),
    MERGEFIELD("mergefield"),
    NUMERIC("numeric"),
    PARENT("parent"),
    PHONE("phone"),
    PRICE("price"),
    SMS("sms"),
    STATE("state"),
    SUBSCRIPTION("subscription"),
    TEXT("text"),
    TIMESTAMP("timestamp"),
    TIMEZONE("timezone"),
    URL("url");

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
