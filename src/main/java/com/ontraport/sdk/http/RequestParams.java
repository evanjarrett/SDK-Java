package com.ontraport.sdk.http;

import java.util.HashMap;
import java.util.Set;

public class RequestParams extends HashMap<String, Object> {

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return super.entrySet();
    }

    public String getVal(String key) {
        Object super_val = super.get(key);
        if (super_val instanceof Integer) {
            return Integer.toString((Integer) super_val);
        }

        if (super_val instanceof String) {
            return (String) super_val;
        }

        return super_val.toString();
    }

    public int getAsInt(String key) {
        Object super_val = super.get(key);
        if (super_val instanceof Integer) {
            return (Integer) super_val;
        }
        return Integer.parseInt((String) super.get(key));
    }
}
