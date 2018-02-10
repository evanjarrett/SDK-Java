package com.ontraport.sdk.objects;

import com.google.gson.Gson;
import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;
import com.ontraport.sdk.objects.response.Meta;
import com.ontraport.sdk.objects.response.ObjectInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Required(params = {"objectID"})
    public String retrieveCollectionInfo(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/getInfo", "get");
    }

    public String retrieveMeta(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/meta", "get");
    }

    public String retrieveCustomObjects(Map<String, String> requestParams) throws RequiredParamsException {

        String multi = retrieveMeta(requestParams);
        Meta meta = gson.fromJson(multi, Meta.class);
        Map<Integer, Meta.Data> custom_objects = new HashMap<>();
        for(Map.Entry<String, Meta.Data> entry : meta.getData().entrySet()) {
            Integer id = Integer.valueOf(entry.getKey());
            if (id >= 10000) {
                custom_objects.put(id, entry.getValue());
            }
        }
        return gson.toJson(custom_objects);
    }

    @Required(params = {"objectID", "id"})
    public String retrieveSingle(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpoint, "get");
    }

    @Required(params = {"objectID"})
    public String retrieveMultiple(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural, "get");
    }

    public String retrieveMultiplePaginated(Map<String, String> requestParams) throws RequiredParamsException {

        requestParams.putIfAbsent("start", "0");
        requestParams.putIfAbsent("range", "50");

        String col = retrieveCollectionInfo(requestParams);
        ObjectInfo decoded = gson.fromJson(col, ObjectInfo.class);
        int count = decoded.getCount();

        int start = Integer.parseInt(requestParams.get("start"));
        int range = Integer.parseInt(requestParams.get("range"));

        ArrayList<String> data = new ArrayList<>();
        while (start < count) {
            String multi = retrieveMultiple(requestParams);
            data.add(multi);
            start += range;
            requestParams.put("start", String.valueOf(start));
        }
        return gson.toJson(data);
    }

    @Required(params = {"objectID"})
    public String retrieveAllWithTag(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/tag", "get");
    }

    @Required(params = {"objectID", "email"})
    public String retrieveIdByEmail(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/getByEmail", "get");
    }

    @Required(params = {"objectID"})
    public String create(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural, "post");
    }

    @Required(params = {"objectID"})
    public String update(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural, "put");
    }

    @Required(params = {"objectID"})
    public String saveorupdate(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/saveorupdate", "post");
    }

    @Required(params = {"objectID", "id"})
    public String deleteSingle(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpoint, "delete");
    }

    @Required(params = {"objectID"})
    public String deleteMultiple(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural, "delete");
    }

    @Required(params = {"ids"})
    public String pause(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/pause", "post");
    }

    @Required(params = {"ids"})
    public String unpause(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/unpause", "post");
    }

    @Required(params = {"ids", "add_list"})
    public String addToSequence(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/sequence", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String removeFromSequence(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/sequence", "delete");
    }

    @Required(params = {"ids", "add_list"})
    public String addTag(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/tag", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String removeTag(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/tag", "delete");
    }

    @Required(params = {"ids", "add_names"})
    public String addTagByName(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/tagByName", "post");
    }

    @Required(params = {"ids", "remove_names"})
    public String removeTagByName(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/tagByName", "delete");
    }

    @Required(params = {"ids", "add_list"})
    public String subscribe(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/subscribe", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String unsubscribe(Map<String, String> requestParams) throws RequiredParamsException {
        checkRequiredParams(requestParams);
        return client.request(requestParams, _endpointPlural + "/subscribe", "delete");
    }

    private void checkRequiredParams(Map<String, String> requestParams) throws RequiredParamsException {
        try {
            String method_name = Thread.currentThread().getStackTrace()[1].getMethodName();
            Method m = getClass().getMethod(method_name, Map.class);
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
