package com.ontraport.sdk.objects;

import com.google.gson.Gson;
import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;
import com.ontraport.sdk.objects.response.ObjectInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class AbstractObject {

    protected enum ContentType {
        JSON("json"),
        FORM("form");

        private final String _ct;

        ContentType(final String _ct) {
            this._ct = _ct;
        }

        public String toString() {
            return _ct;
        }

    }

    protected Ontraport client;
    protected Gson gson = new Gson();

    protected String _endpoint;
    protected String _endpointPlural;

    public AbstractObject(Ontraport client) {
        this.client = client;
    }

    public String retrieveCollectionInfo(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/getInfo", "get");
    }

    public String retrieveMeta(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/meta", "get");
    }

    @Required(params = {"id"})
    public String retrieveSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint, "get");
    }

    public String retrieveMultiple(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural, "get");
    }

    public String retrieveMultiplePaginated(RequestParams params) throws RequiredParamsException {

        params.putIfAbsent("start", "0");
        params.putIfAbsent("range", "50");

        String col = retrieveCollectionInfo(params);
        ObjectInfo decoded = gson.fromJson(col, ObjectInfo.class);
        int count = decoded.getCount();

        int start = Integer.parseInt(params.get("start"));
        int range = Integer.parseInt(params.get("range"));

        ArrayList<String> data = new ArrayList<>();
        while (start < count) {
            String multi = retrieveMultiple(params);
            data.add(multi);
            start += range;
            params.put("start", String.valueOf(start));
        }
        return gson.toJson(data);
    }

    public String create(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural, "post");
    }

    @Required(params = {"id"})
    public String update(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural, "put");
    }

    public String saveorupdate(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/saveorupdate", "post");
    }

    @Required(params = {"id"})
    public String deleteSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint, "delete");
    }

    public String deleteMultiple(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural, "delete");
    }

    protected void checkRequiredParams(RequestParams params) throws RequiredParamsException {
        try {
            String method_name = Thread.currentThread().getStackTrace()[2].getMethodName();
            Method m = getClass().getMethod(method_name, RequestParams.class);
            String[] requiredParams = m.getAnnotation(Required.class).params();

            ArrayList<String> missingParams = new ArrayList<>();

            for (String requiredParam : requiredParams) {
                if (!params.containsKey(requiredParam)) {
                    // Covers special case: when ids is required, group_ids can be substituted
                    if (requiredParam.equals("ids")) {
                        if (!params.containsKey("group_ids")) {
                            missingParams.add(requiredParam);
                        }
                    }
                    else {
                        missingParams.add(requiredParam);
                    }
                }
            }
            if (missingParams.size() > 0) {
                throw new RequiredParamsException(String.join(",", missingParams));
            }
        }
        catch (NoSuchMethodException ignored) {
        }
    }
}
