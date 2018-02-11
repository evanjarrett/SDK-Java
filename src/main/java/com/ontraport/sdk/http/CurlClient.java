package com.ontraport.sdk.http;

import com.google.gson.Gson;
import com.ontraport.sdk.objects.RequestParams;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurlClient {

    public static final int HTTP_OK = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_RATE_LIMIT = 429;
    public static final int HTTP_ERROR = 500;

    public static final String RATE_LIMIT_RESET = "x-rate-limit-reset";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Map<String, String> _requestHeaders = new HashMap<>();

    private int _lastStatusCode;

    public CurlClient(String siteID, String apiKey) {
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


    public String httpRequest(RequestParams params, String url, String method, String[] options) {
        return httpRequest(params, url, method);
    }

    public String httpRequest(RequestParams params, String url, String method) {
        HttpUrl.Builder http_builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        if (method.toLowerCase().equals("get")) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                http_builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        String http_url = http_builder.build().toString();
        Request.Builder request_builder = new Request.Builder().url(http_url);

        if (!method.toLowerCase().equals("get")) {
            Gson gson = new Gson();
            RequestBody post_body = RequestBody.create(JSON, gson.toJson(params));
            request_builder.post(post_body);
        }

        for (Map.Entry<String, String> entry : getRequestHeaders().entrySet()) {
            request_builder.addHeader(entry.getKey(), entry.getValue());
        }

        Request requestParams = request_builder.build();

        try (Response response = new OkHttpClient().newCall(requestParams).execute()) {
            setLastStatusCode(response.code());
            return Objects.requireNonNull(response.body()).string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
