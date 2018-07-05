package com.ontraport.sdk.models.fieldeditor;

import com.ontraport.sdk.exceptions.FieldEditorException;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.models.Requestable;

import java.util.ArrayList;
import java.util.List;

public class ObjectSection implements Requestable {

    private String _name;
    private String _description;
    private Column[] _columns = new Column[3];

    public ObjectSection(String name, List<ObjectField>[] fields) {
        new ObjectSection(name, "", fields);
    }

    public ObjectSection(String name, String description, List<ObjectField>[] fields) {
        _name = name;
        _description = description;
        for (int i = 0; i < fields.length && i < 3; i++) {
            _columns[i] = (Column) fields[i];
        }
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

    @Override
    public RequestParams toRequestParams() {
        return null;
    }

    private class Column extends ArrayList<ObjectField> {

    }
}
