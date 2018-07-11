package com.ontraport.sdk.http;

import java.util.Map;

public class ObjectInfo extends AbstractResponse {
    private Data data;

    public class Data implements AbstractResponse.Data {
        String[] listFields;
        Map<String, Object> listFieldSettings;
        String count;

        public String[] getListFields() {
            return listFields;
        }

        public FieldSettings[] getListFieldSettings() {
            Integer i = 0;
            FieldSettings[] fieldSettings = new FieldSettings[listFieldSettings.size()-1];
            while (listFieldSettings.containsKey(i.toString())) {
                Map<String, String> setting = (Map<String, String>) listFieldSettings.get(i.toString());
                fieldSettings[i] = new FieldSettings(setting.get("name"), setting.get("width"), setting.get("sortDir"));
                i++;
            }
            return fieldSettings;
        }

        public String getViewMode() {
            return (String) listFieldSettings.get("viewMode");
        }
    }

    public class FieldSettings {
        String name;
        String width;
        String sortDir;

        public FieldSettings() {

        }

        public FieldSettings(String name, String width, String sortDir) {
            this.name = name;
            this.width = width;
            this.sortDir = sortDir;
        }

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
