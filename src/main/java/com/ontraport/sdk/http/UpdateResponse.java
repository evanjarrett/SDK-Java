package com.ontraport.sdk.http;

import java.util.HashMap;

public class UpdateResponse extends AbstractResponse {
    private Data data;

    public class Data implements AbstractResponse.Data {
        HashMap<String, String> attrs;

        public String getUpdateTime() {
            return attrs.getOrDefault("dlm", "0");
        }
    }

    public Data getData() {
        return data;
    }

}
