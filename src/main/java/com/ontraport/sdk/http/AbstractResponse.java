package com.ontraport.sdk.http;

import com.google.gson.Gson;

public abstract class AbstractResponse {
    private int code;
    private String account_id;

    public interface Data {
    }

    public String toString() {
        return new Gson().toJson(this, getClass());
    }

}
