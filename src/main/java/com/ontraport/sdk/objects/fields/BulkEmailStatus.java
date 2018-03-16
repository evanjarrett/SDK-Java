package com.ontraport.sdk.objects.fields;

import java.util.HashMap;
import java.util.Map;

public enum BulkEmailStatus {
    OPT_OUT(0, "Opted-out"),
    OPT_IN(1, "Opted-in"),
    DOUBLE_OPT_IN(2, "Double opt-in"),
    HARD_BOUNCE(-2, "Hard bounce"),
    UNDER_REVIEW(-5, "Under review");

    private final int value;
    private final String name;
    private static Map<Integer, BulkEmailStatus> map = new HashMap<>();

    BulkEmailStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    static {
        for (BulkEmailStatus bes : BulkEmailStatus.values()) {
            map.put(bes.value, bes);
        }
    }

    public static BulkEmailStatus valueOf(int bes) {
        return map.get(bes);
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
