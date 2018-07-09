package com.ontraport.sdk.http;

import java.util.List;

public class FieldEditorResponse extends AbstractResponse {
    private FieldEditorResponse.Data data;

    public class Data implements AbstractResponse.Data {
        Integer id;
        String name;
        String description;
        List<Field>[] fields;

        public Integer getId() {
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
        Integer id;
        String alias;
        String field;
        String type;
        Integer required;
        Integer unique;
        Integer editable;
        Integer deletable;
        String options;

        public Integer getId() {
            return id;
        }

        public String getAlias() {
            return alias;
        }

        public String getField() {
            return field;
        }

        public String getType() {
            return type;
        }

        public boolean isRequired() {
            return required.equals("1");
        }

        public boolean isUnique() {
            return unique.equals("1");
        }

    }
}
