package com.ontraport.sdk.http;

import okhttp3.MediaType;

import java.util.HashMap;
import java.util.Map;

public abstract class Client {

    public static final int HTTP_OK = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_RATE_LIMIT = 429;
    public static final int HTTP_ERROR = 500;

    public static final String RATE_LIMIT_RESET = "x-rate-limit-reset";

    protected Map<String, String> _requestHeaders = new HashMap<>();

    protected int _lastStatusCode;

    public Client() {

    }

    public Client(String siteID, String apiKey) {
        setRequestHeader("Api-Appid", siteID);
        setRequestHeader("Api-Key", apiKey);
    }

    public void setCredentials(String siteID, String apiKey) {
        setRequestHeader("Api-Appid", siteID);
        setRequestHeader("Api-Key", apiKey);
    }

    public void setRequestHeader(String header, String value) {
        _requestHeaders.put(header, value);
    }

    public Map<String, String> getRequestHeaders() {
        return _requestHeaders;
    }

    public int getLastStatusCode() {
        return _lastStatusCode;
    }

    public void setLastStatusCode(int statusCode) {
        _lastStatusCode = statusCode;
    }

    public SingleResponse httpRequest(RequestParams params, String url, String method) {
        return httpRequest(params, url, method, SingleResponse.class);
    }

    abstract public <T extends AbstractResponse> T httpRequest(RequestParams params, String url, String method, Class<T> responseClazz);
}
