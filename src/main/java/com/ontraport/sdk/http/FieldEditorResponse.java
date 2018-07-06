package com.ontraport.sdk.http;

import java.util.List;
import java.util.Map;

public abstract class FieldEditorResponse extends AbstractResponse {
    private FieldEditorResponse.Data data;

    public class Data implements AbstractResponse.Data {
        String id;
        String name;
        String description;
        List<Field>[] fields;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public List<Field>[] getFields() {
            return fields;
        }
    }

    public FieldEditorResponse.Data getData() {
        return data;
    }

    public class Field {
        String id;
        String alias;
        String field;
        String type;
        String required;
        String unique;
        String editable;
        Map<String, String> options;
    }
}
