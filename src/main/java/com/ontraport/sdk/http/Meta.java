package com.ontraport.sdk.http;

import java.util.Map;

public class Meta extends AbstractResponse {
    Map<String, Data> data;

    public class Data implements AbstractResponse.Data {
        String name;
        Map<String,Field> fields;
    }

    public class Field {
        String alias;
        String type;
        String parent_object;
        Map<String, String> options;
    }

    public Map<String, Data> getData() {
        return data;
    }
}
