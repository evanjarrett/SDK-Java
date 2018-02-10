package com.ontraport.sdk.objects.response;

public class ObjectInfo extends Response {
    private Data data = new Data();

    public class Data implements Response.Data {
        String[] listFields;
        FieldSettings[] listFieldSettings;
        String count;
    }

    public class FieldSettings {
        String name;
        String width;
    }

    public int getCount() {
        return Integer.parseInt(data.count);
    }

    public Data getData() {
        return data;
    }
}
