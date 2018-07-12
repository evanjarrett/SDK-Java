package com.ontraport.sdk.http;

import java.util.Map;

public class FieldEditorResponse extends AbstractResponse {
    private Map<String, SectionResponse.Section> data;

    public Map<String, SectionResponse.Section> getData() {
        return data;
    }
}
