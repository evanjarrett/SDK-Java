package com.ontraport.sdk.objects.fields;

import java.util.HashMap;
import java.util.Map;

public enum BulkSMSStatus {
    OPT_OUT(0, "Opted-out"),
    OPT_IN(1, "Opted-in"),
    DOUBLE_OPT_IN(2, "Double opt-in"),
    HARD_BOUNCE(-2, "Hard bounce");

    private final int value;
    private final String name;
    private static Map<Integer, BulkSMSStatus> map = new HashMap<>();

    BulkSMSStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    static {
        for (BulkSMSStatus bss : BulkSMSStatus.values()) {
            map.put(bss.value, bss);
        }
    }

    public static BulkSMSStatus valueOf(int bss) {
        return map.get(bss);
    }

    public static String getNameFromValue(int value) {
        return map.get(value).getName();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
