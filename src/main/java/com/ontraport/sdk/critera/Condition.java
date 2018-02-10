package com.ontraport.sdk.critera;

import java.util.ArrayList;
import java.util.List;

class Field {
    private String field;

    protected Field(String field) {
        this.field = field;
    }
}

class Value<T> {
    private List<Value<T>> list = null;
    private T value = null;

    protected Value(T value) {
        this.value = value;
    }

    protected Value(List<Value<T>> list) {
        this.list = list;
    }
}

public class Condition<T> {
    private Field field;
    private String op;
    private Value<T> value;

    public Condition (String field, Operator op, T value) {
        this.field = new Field(field);
        this.op = op.toString();
        this.value = new Value<>(value);
    }

    public Condition (String field, Operator op, List<T> value) {
        this.field = new Field(field);
        this.op = op.toString();

        ArrayList<Value<T>> tmp = new ArrayList<>();
        for (T v : value) {
            Value<T> val = new Value<>(v);
            tmp.add(val);
        }
        this.value = new Value<>(tmp);
    }

}
