package com.ontraport.sdk;


import com.ontraport.sdk.http.CurlClient;
import com.ontraport.sdk.objects.Objects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ontraport {

    public static final String REQUEST_URL = "https://api.ontraport.com";

    public static final int API_VERSION = 1;

    private static Map<String, Objects> _apiInstances = new ConcurrentHashMap<>();
    protected String[] _customObjects;
    protected CurlClient _httpClient;

    private String _siteID;
    private String _apiKey;

    public Ontraport(String siteID, String apiKey) {
        this.setCredentials(siteID, apiKey);
        this.setHttpClient(createHttpClient());
    }

    public Ontraport(String siteID, String apiKey, CurlClient client) {
        this.setCredentials(siteID, apiKey);
        this.setHttpClient(client);
    }

    public void setCredentials(String siteID, String apiKey) {
        _siteID = siteID;
        _apiKey = apiKey;
    }

    public CurlClient createHttpClient() {
        return new CurlClient(_siteID, _apiKey);
    }

    public void setHttpClient(CurlClient client) {
        _httpClient = client;
    }

    public CurlClient getHttpClient() {
        return _httpClient;
    }

    public Objects objects() {
        Objects obj = new Objects(this);
        _apiInstances.put("objects", obj);
        return obj;
    }

    public String request(Map<String, String> requestParams, String url, String method, String[] options) {
        String str_url = buildEndpoint(url);

        return getHttpClient().httpRequest(requestParams, str_url, method, options);
    }

    public String request(Map<String, String> requestParams, String url, String method) {
        String str_url = buildEndpoint(url);

        return getHttpClient().httpRequest(requestParams, str_url, method);
    }

    public int getLastStatusCode() {
        return getHttpClient().getLastStatusCode();
    }

    public String buildEndpoint(String extendedURL) {
        return REQUEST_URL + "/" + API_VERSION + "/" + extendedURL;
    }
}
