package com.ontraport.sdk.http;

import java.util.HashMap;
import java.util.Set;

public class RequestParams extends HashMap<String, Object> {

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return super.entrySet();
    }

    public int getAsInt(Object key) {
        return Integer.parseInt((String) super.get(key));
    }
}
