package com.ontraport.sdk.http;

public class FieldResponse extends AbstractResponse {
    private Field data;

    public class Field implements AbstractResponse.Data {
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
            return required.equals(1);
        }

        public boolean isUnique() {
            return unique.equals(1);
        }

        public boolean isEditable() {
            return editable.equals(1);
        }

        public boolean isDeletable() {
            return deletable.equals(1);
        }
    }

    public Field getData() {
        return data;
    }
}
