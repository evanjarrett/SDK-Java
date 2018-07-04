package com.ontraport.sdk.models.FieldEditor;

import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.models.Requestable;
import com.ontraport.sdk.objects.fields.FieldType;

import java.util.Map;

public class ObjectField implements Requestable {

    private int _id;
    private String _alias;
    private String _field;
    private FieldType _type;
    private boolean _required;
    private boolean _unique;
    private DropOption _drop_options;

    public ObjectField(String alias, FieldType type) {
        new ObjectField(alias, type, false, false);
    }

    public ObjectField(String alias, FieldType type, boolean required, boolean unique) {
        _alias = alias;
        _type = type;
        _required = required;
        _unique = unique;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getAlias() {
        return _alias;
    }

    public void setAlias(String alias) {
        _alias = alias;
    }

    public String getField() {
        return _field;
    }

    public void setField(String field) {
        _field = field;
    }

    public void addDropOptions(Map<String, String> options) {
        _drop_options = new DropOption("add", options);
    }

    public void removeDropOptions(Map<String, String> options) {
        _drop_options = new DropOption("remove", options);
    }

    public void replaceDropOptions(Map<String, String> options) {
        _drop_options = new DropOption("replace", options);
    }

    @Override
    public RequestParams toRequestParams() {
        return null;
    }

    public void expandFieldType() {
        if (_type.equals(FieldType.TEXT)) {
            _type = FieldType.LONGTEXT;
        }
    }

    @Override
    public String toString() {
        return getField();
    }

}
