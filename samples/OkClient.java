package com.ontraport.sdk.http;

import com.google.gson.Gson;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class OkClient extends Client {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient okHttpClient = new OkHttpClient();

    public OkClient() {

    }

    public OkClient(String siteID, String apiKey) {
        super(siteID, apiKey);
    }

    public OkClient(String siteID, String apiKey, File cacheDir) {
        super(siteID, apiKey);

        if (cacheDir != null) {
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(cacheDir, cacheSize);
            okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .build();
        }
    }

    public SingleResponse httpRequest(RequestParams params, String url, String method) {
        return httpRequest(params, url, method, SingleResponse.class);
    }

    public <T extends AbstractResponse> T httpRequest(RequestParams params, String url, String method, Class<T> responseClazz) {
        HttpUrl.Builder http_builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        if (method.toLowerCase().equals("get")) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                http_builder.addQueryParameter(entry.getKey(), (String) entry.getValue());
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

        String json = null;
        try {
            Response response = okHttpClient.newCall(requestParams).execute();
            setLastStatusCode(response.code());
            json = Objects.requireNonNull(response.body()).string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(json, responseClazz);
    }
}
