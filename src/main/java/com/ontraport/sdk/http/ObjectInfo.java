package com.ontraport.sdk.http;

public class ObjectInfo extends AbstractResponse {
    private Data data;

    public class Data implements AbstractResponse.Data {
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
