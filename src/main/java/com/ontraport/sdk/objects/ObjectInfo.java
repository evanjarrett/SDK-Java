package com.ontraport.sdk.objects;

public class ObjectInfo extends Response {
    private Data data = new Data();

    class Data {
        String[] listFields;
        FieldSettings[] listFieldSettings;
        String count;
    }

    class FieldSettings {
        String name;
        String width;
    }

    public int getCount() {
        return Integer.parseInt(data.count);
    }
}
