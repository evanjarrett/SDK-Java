package com.ontraport.sdk.http;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

public class URLClient extends Client {

    public URLClient() {

    }

    public URLClient(String siteID, String apiKey) {
        super(siteID, apiKey);
    }

    public SingleResponse httpRequest(RequestParams params, String url, String method) throws NullResponseException {
        return httpRequest(params, url, method, SingleResponse.class);
    }

    public <T extends AbstractResponse> T httpRequest(RequestParams params, String baseURL, String method, Class<T> responseClazz) throws NullResponseException {

        String http_url = baseURL;

        if (method.toLowerCase().equals("get")) {
            try {
                http_url += buildQueryString(params);
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        String json = "";
        try {
            URL url = new URL(http_url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            for (Map.Entry<String, String> entry : getRequestHeaders().entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            if (!method.toLowerCase().equals("get")) {
                connection.setDoOutput(true);
                connection.setRequestMethod(method.toUpperCase());

                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(gson.toJson(params));
                wr.flush();
                wr.close();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            json = sb.toString();
            rd.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, responseClazz);
    }

    private String buildQueryString(RequestParams params) throws UnsupportedEncodingException {
        ArrayList<String> paramsList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String temp = URLEncoder.encode(entry.getKey(), "UTF-8") + "=" +
                    URLEncoder.encode((String) entry.getValue(), "UTF-8");
            paramsList.add(temp);
        }
        return "?" + String.join("&", paramsList);
    }
}
