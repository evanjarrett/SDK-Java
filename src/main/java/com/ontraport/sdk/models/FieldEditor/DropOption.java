package com.ontraport.sdk.models.FieldEditor;

import java.util.HashMap;
import java.util.Map;

public class DropOption {

    private String _key = "replace";
    private Map<String, String> _values = new HashMap<>();

    public DropOption(String key, Map<String, String> values) {
        _key = key;
        _values = values;
    }
}
