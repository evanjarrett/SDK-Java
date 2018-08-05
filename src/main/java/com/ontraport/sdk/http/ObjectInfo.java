package com.ontraport.sdk.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectInfo extends AbstractResponse {
    private Data data;

    public class Data implements AbstractResponse.Data {
        String[] listFields;
        Object listFieldSettings;
        String count;

        public String[] getListFields() {
            return listFields;
        }

        public List<FieldSettings> getListFieldSettings() {
            List<FieldSettings> fieldSettings = new ArrayList<>();

            if (listFieldSettings instanceof Map) {
                Integer i = 0;
                Map<String, Object> settings = (Map<String, Object>) listFieldSettings;
                while (settings.containsKey(i.toString())) {
                    Map<String, String> setting = (Map<String, String>) settings.get(i.toString());
                    fieldSettings.add(new FieldSettings(setting.get("name"), setting.get("width"), setting.get("sortDir")));
                    i++;
                }
                return fieldSettings;
            }
            else if (listFieldSettings instanceof List) {
                List<Object> settings = (List<Object>) listFieldSettings;
                for (Object obj : settings) {
                    if (obj instanceof Map) {
                        Map<String, String> setting = (Map<String, String>) obj;
                        fieldSettings.add(new FieldSettings(setting.get("name"), setting.get("width"), setting.get("sortDir")));
                    }
                }
                return fieldSettings;
            }
            return null;
        }

        public String getViewMode() {
            return "0";
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
