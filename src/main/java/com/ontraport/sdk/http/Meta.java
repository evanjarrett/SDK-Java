package com.ontraport.sdk.http;

import java.util.Map;

public class Meta extends AbstractResponse {
    Map<String, Data> data;

    public class Data implements AbstractResponse.Data {
        String name;
        String type;
        Map<String, Field> fields;

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
        
        public String getEditable() {
            return type;
        }
        
        public String getRequired() {
            return null;
        }

        public Map<String, Field> getFields() {
            return fields;
        }
    }

    public class Field {
        String alias;
        String type;
        String parent_object;
        Map<String, String> options;

        public String getAlias() {
            return alias;
        }

        public String getType() {
            return type;
        }

        public boolean hasParent() {
            return type.equals("parent") && parent_object != null;
        }

        public String getParent() {
            return parent_object;
        }

        public boolean hasOptions() {
            return (type.equals("drop") || type.equals("list")) && options != null;
        }

        public Map<String, String> getOptions() {
            return options;
        }
    }

    public Map<String, Data> getData() {
        return data;
    }
}
