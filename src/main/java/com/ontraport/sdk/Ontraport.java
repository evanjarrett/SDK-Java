package com.ontraport.sdk;


import com.ontraport.sdk.http.AbstractResponse;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.CurlClient;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.objects.AbstractObject;
import com.ontraport.sdk.objects.Contacts;
import com.ontraport.sdk.objects.CustomObjects;
import com.ontraport.sdk.objects.Objects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ontraport {

    public static final String REQUEST_URL = "https://api.ontraport.com";

    public static final int API_VERSION = 1;

    private static Map<String, AbstractObject> _apiInstances = new ConcurrentHashMap<>();
    protected Map<String, CustomObjects> _customObjects = new ConcurrentHashMap<>();
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

    public CustomObjects custom() {
//        try {
//            if (_customObjects.isEmpty()) {
//                _customObjects.put(objects().retrieveCustomObjects(null));
//            }
//        }
//        catch (RequiredParamsException ignored) {
//        }
        CustomObjects obj = new CustomObjects(this);
        _apiInstances.put("customObjects", obj);
        return obj;
    }

    public Objects objects() {
        Objects obj = new Objects(this);
        _apiInstances.put("objects", obj);
        return obj;
    }

    public Contacts contacts() {
        Contacts obj = new Contacts(this);
        _apiInstances.put("contacts", obj);
        return obj;
    }

    public SingleResponse request(RequestParams params, String url, String method, String[] options) {
        String str_url = buildEndpoint(url);

        return getHttpClient().httpRequest(params, str_url, method, options);
    }

    public SingleResponse request(RequestParams params, String url, String method) {
        String str_url = buildEndpoint(url);

        return getHttpClient().httpRequest(params, str_url, method);
    }

    public <T extends AbstractResponse> T request(RequestParams params, String url, String method, Class<T> clazz) {
        String str_url = buildEndpoint(url);

        return getHttpClient().httpRequest(params, str_url, method, clazz);
    }

    public int getLastStatusCode() {
        return getHttpClient().getLastStatusCode();
    }

    public String buildEndpoint(String extendedURL) {
        return REQUEST_URL + "/" + API_VERSION + "/" + extendedURL;
    }
}
