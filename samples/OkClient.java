import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ontraport.sdk.exceptions.NullResponseException
import com.ontraport.sdk.http.AbstractResponse;
import com.ontraport.sdk.http.Client;
import com.ontraport.sdk.http.ListResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.SingleResponse;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class OkClient extends Client {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

    private OkHttpClient okHttpClient = new OkHttpClient();
    private boolean force_network = false;

    public OkClient(File cacheDir) {
        setCache(cacheDir);
    }

    private void setCache(File cacheDir) {
        okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(cacheDir, CACHE_SIZE))
                .addNetworkInterceptor(new ResponseCacheInterceptor())
                .addInterceptor(new OfflineResponseCacheInterceptor())
                .build();
    }

    public void forceNetwork() {
        force_network = true;
    }

    public SingleResponse httpRequest(RequestParams params, String url, String method) {
        return httpRequest(params, url, method, SingleResponse.class);
    }

    public <T extends AbstractResponse> T httpRequest(RequestParams params, String url, String method, Class<T> responseClazz) {
        HttpUrl.Builder http_builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        method = method.toUpperCase();
        if (method.equals("GET")) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object super_val = entry.getValue();
                String val = super_val.toString();
                if (super_val instanceof Integer) {
                    val = Integer.toString((Integer) super_val);
                }
                if (super_val instanceof String) {
                    val = (String) super_val;
                }
                http_builder.addQueryParameter(entry.getKey(), val);
            }
        }

        String http_url = http_builder.build().toString();
        Request.Builder request_builder = new Request.Builder().url(http_url);
        if (force_network) {
            System.out.println("Forcing network request");
            request_builder.cacheControl(CacheControl.FORCE_NETWORK);
            force_network = false;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ListResponse.class, new FailSafeJsonDeserializer())
                .create();
        if (!method.equals("GET")) {
            RequestBody post_body = RequestBody.create(JSON, gson.toJson(params));
            request_builder.method(method, post_body);
        }

        for (Map.Entry<String, String> entry : getRequestHeaders().entrySet()) {
            request_builder.addHeader(entry.getKey(), entry.getValue());
        }

        Request requestParams = request_builder.build();

        String json = null;
        try {
            Response response = okHttpClient.newCall(requestParams).execute();
            if (response.cacheResponse() != null) {
                System.out.println("Getting from cache");
            }
            setLastStatusCode(response.code());
            json = Objects.requireNonNull(response.body()).string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (json == null || json.isEmpty()) {
            throw new NullResponseException();
        }

        return gson.fromJson(json, responseClazz);
    }

    private static class ResponseCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build();
        }
    }

    private static final class FailSafeJsonDeserializer
            implements JsonDeserializer<ListResponse> {

        @Override
        public ListResponse deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context)
                throws JsonParseException {
            JsonArray data_arr = element.getAsJsonObject().getAsJsonArray("data");
            for (JsonElement elem : data_arr) {
                if (elem.isJsonObject()) {
                    JsonObject jobj= elem.getAsJsonObject();
                    for (Iterator<Map.Entry<String, JsonElement>> it = jobj.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry<String, JsonElement> entry = it.next();
                        if (entry.getValue().isJsonObject()) {
                            // Remove incorrect types
                            it.remove();
                        }
                    }
                }
            }
            return new Gson().fromJson(element, ListResponse.class);
        }
    }
}
