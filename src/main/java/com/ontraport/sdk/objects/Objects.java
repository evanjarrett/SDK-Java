package com.ontraport.sdk.objects;

import com.google.gson.Gson;
import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class Objects {

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

    protected String _endpoint = "object";
    protected String _endpointPlural = "objects";

    public Objects(Ontraport client) {
        this.client = client;
    }

    @Required(params = {"objectID", "id"})
    public String retrieveSingle(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams,new Object(){}
                .getClass()
                .getEnclosingMethod()
                .getName());
        return client.request(requestParams, _endpoint, "get");
    }

    @Required(params = {"objectID"})
    public String retrieveMultiple(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams,new Object(){}
                .getClass()
                .getEnclosingMethod()
                .getName());
        return client.request(requestParams, _endpointPlural, "get");
    }

    @Required(params = {"objectID"})
    public String retrieveMultiplePaginated(Map<String, String> requestParams) throws RequiredParamsException {

        requestParams.putIfAbsent("start", "0");
        requestParams.putIfAbsent("range", "50");

        String col = retrieveCollectionInfo(requestParams);
        ObjectInfo decoded = gson.fromJson(col, ObjectInfo.class);
        int count = decoded.getCount();

        int start = Integer.parseInt(requestParams.get("start"));
        int range = Integer.parseInt(requestParams.get("range"));

        ArrayList<String> data = new ArrayList<>();
        while ( start < count) {
            String multi = retrieveMultiple(requestParams);
            data.add(multi);
            start += range;
            requestParams.put("start", String.valueOf(start));
        }
        return gson.toJson(data);
    }

    @Required(params = {"objectID"})
    public String retrieveCollectionInfo(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams,new Object(){}
                .getClass()
                .getEnclosingMethod()
                .getName());
        return client.request(requestParams, _endpointPlural + "/getInfo", "get");
    }

    private void checkRequiredParams(Map<String, String> requestParams, String method) throws RequiredParamsException {
        try {
            Method m = getClass().getMethod(method, Map.class);
            String[] requiredParams = m.getAnnotation(Required.class).params();

            ArrayList<String> missingParams = new ArrayList<>();

            for (String requiredParam : requiredParams) {
                if (!requestParams.containsKey(requiredParam)) {
                    // Covers special case: when ids is required, group_ids can be substituted
                    if (requiredParam.equals("ids")) {
                        if (!requestParams.containsKey("group_ids")) {
                            missingParams.add(requiredParam);
                        }
                    }
                    else {
                        missingParams.add(requiredParam);
                    }
                }
            }
            if (missingParams.size() > 0) {
                String params = String.join(",", missingParams);
                throw new RequiredParamsException(params);
            }
        }
        catch (NoSuchMethodException ignored) {
        }

    }
}
