package com.ontraport.sdk;

public class Ontraport {

    public static final String REQUEST_URL = "https://api.ontraport.com";

    public static final int API_VERSION = 1;

    protected String[] _apiInstances;
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
        _apiKey = apiKey;
        _siteID = siteID;
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

    public String request(String[] requestParams, String url, String method, String[] requiredParams, String[] options) {
        CurlClient client = getHttpClient();
        String str_url = buildEndpoint(url);

        return client.httpRequest(requestParams, str_url, method, requiredParams, options);
    }

    public int getLastStatusCode() {
        return getHttpClient().getLastStatusCode();
    }

    public String buildEndpoint(String extendedURL) {
        return REQUEST_URL + "/" + API_VERSION + "/" + extendedURL;
    }
}
