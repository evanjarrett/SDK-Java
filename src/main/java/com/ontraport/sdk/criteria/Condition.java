package com.ontraport.sdk.criteria;

import java.util.ArrayList;
import java.util.List;

class Field {
    private String _field;

    protected Field(String field) {
        _field = field;
    }
}

class Value<T> {
    private List<Value<T>> _list = null;
    private T _value = null;

    protected Value(T value) {
        _value = value;
    }

    protected Value(List<Value<T>> list) {
        _list = list;
    }
}

public class Condition<T> {
    private Field _field;
    private String _op;
    private Value<T> _value;

    public Condition (String field, Operator op, T value) {
        _field = new Field(field);
        _op = op.toString();
        _value = new Value<>(value);
    }

    public Condition (String field, Operator op, List<T> value) {
        _field = new Field(field);
        _op = op.toString();

        ArrayList<Value<T>> tmp = new ArrayList<>();
        for (T v : value) {
            Value<T> val = new Value<>(v);
            tmp.add(val);
        }
        _value = new Value<>(tmp);
    }

}
