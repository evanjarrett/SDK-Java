package com.ontraport.sdk.http;

public class ObjectInfo extends AbstractResponse {
    private Data data;

    public class Data implements AbstractResponse.Data {
        String[] listFields;
        FieldSettings[] listFieldSettings;
        String count;

        public String[] getListFields() {
            return listFields;
        }

        public FieldSettings[] getListFieldSettings() {
            return listFieldSettings;
        }
    }

    public class FieldSettings {
        String name;
        String width;
        String sortDir;

        public String getName() {
            return name;
        }

        public String getSortDir() {
            return sortDir;
        }

        public String getWidth() {
            return width;
        }
    }

    public int getCount() {
        return Integer.parseInt(data.count);
    }

    public Data getData() {
        return data;
    }
}
