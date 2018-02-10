package com.ontraport.sdk.objects.response;

import java.util.HashMap;
import java.util.Map;

public class Meta extends Response {
    HashMap<String, Data> data;

    public class Data implements Response.Data {
        String name;
        HashMap<String,Field> fields;
    }

    public class Field {
        String alias;
        String type;
        String parent_object;
        HashMap<String, String> options;
    }

    public Map<String, Data> getData() {
        return data;
    }
}
