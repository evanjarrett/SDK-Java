package com.ontraport.sdk.models.fieldeditor;

import com.google.gson.Gson;
import com.ontraport.sdk.exceptions.FieldEditorException;
import com.ontraport.sdk.http.FieldResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.SectionResponse;
import com.ontraport.sdk.models.Requestable;

import java.util.ArrayList;
import java.util.List;

public class ObjectSection implements Requestable {

    private String _name;
    private String _description;
    private Column[] _columns = {new Column(), new Column(), new Column()};

    public ObjectSection(String name, String description) {
        this(name, "", new Column[]{new Column(), new Column(), new Column()});
    }

    public ObjectSection(String name, Column[] fields) {
        this(name, "", fields);
    }

    public ObjectSection(String name, String description, Column[] fields) {
        _name = name;
        _description = description;
        for (int i = 0; i < fields.length && i < 3; i++) {
            _columns[i] = fields[i];
        }
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public ObjectField getFieldByAlias(String alias) {
        for (Column column : _columns) {
            for (ObjectField field : column) {
                if (field.getAlias().equalsIgnoreCase(alias)) {
                    return field;
                }
            }
        }
        return null;
    }

    public void putFieldInColumn(int index, ObjectField field) {
        _columns[index].add(field);
    }

    /**
     * @param index  The column index between 0 and 2
     * @param fields A list of fields to append to the column at the given index
     * @throws ArrayIndexOutOfBoundsException If the index is not between 0 and 2
     */
    public void putFieldsInColumn(int index, List<ObjectField> fields) {
        _columns[index].addAll(fields);
    }

    public void updateField(ObjectField field) throws FieldEditorException {
        for (Column c : _columns) {
            for (int j = 0; j < c.size(); j++) {
                String of_field = c.get(j).getField();
                if (field.getField().equalsIgnoreCase(of_field)) {
                    c.set(j, field);
                    break;
                }
            }
        }
        throw new FieldEditorException("Could not find an existing field:" + field.toString() + "in this Section.");
    }

    public static ObjectSection createFromResponse(SectionResponse.Section response) {
        List<FieldResponse.Field>[] columns = response.getFields();
        ObjectSection section = new ObjectSection(response.getName(), response.getDescription());

        for (int i = 0; i < columns.length && i < 3; i++) {
            for (FieldResponse.Field field : columns[i]) {
                section.putFieldInColumn(i, ObjectField.createFromResponse(field));
            }
        }
        return section;
    }

    @Override
    public RequestParams toRequestParams() {
        RequestParams params = new RequestParams();
        params.put("name", _name);
        params.put("description", _description);

        List<List<RequestParams>> field_map = new ArrayList<>(3);
        for (int i = 0; i < _columns.length; i++) {
            field_map.add(_columns[i].toRequestParams());
        }
        params.put("fields", field_map);
        return params;
    }

    @Override
    public String toString() {
        return new Gson().toJson(toRequestParams());
    }

    public static class Column extends ArrayList<ObjectField> {

        public Column() {
            super();
        }

        public List<RequestParams> toRequestParams() {
            List<RequestParams> fields = new ArrayList<>(size());
            for (ObjectField field : this) {
                fields.add(field.toRequestParams());
            }
            return fields;
        }
    }
}
