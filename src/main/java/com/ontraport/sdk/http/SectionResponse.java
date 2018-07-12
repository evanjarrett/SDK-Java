package com.ontraport.sdk.http;

import java.util.List;

public class SectionResponse extends AbstractResponse {
    private Section data;

    public class Section implements AbstractResponse.Data {
        Integer id;
        String name;
        String description;
        List<FieldResponse.Field>[] fields;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public List<FieldResponse.Field>[] getFields() {
            return fields;
        }
    }

    public Section getData() {
        return data;
    }
}
