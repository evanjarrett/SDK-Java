package com.ontraport.sdk.http;

import java.util.Map;

public class ListResponse extends AbstractResponse {
    private Map<String,String>[] data;

    public Map<String,String>[] getData() {
        return data;
    }
}
