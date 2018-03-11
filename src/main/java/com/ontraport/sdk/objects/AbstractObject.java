package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.ListResponse;
import com.ontraport.sdk.http.Meta;
import com.ontraport.sdk.http.ObjectInfo;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.UpdateResponse;

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
    protected String _endpoint;
    protected String _endpointPlural;
    protected int objectID = -1;

    public AbstractObject(Ontraport client) {
        this.client = client;
    }

    protected String getEndpoint() {
        return _endpoint;
    }

    protected void setEndpoint(String endpoint) {
        _endpoint = endpoint;
    }

    protected String getEndpointPlural() {
        return _endpointPlural;
    }

    protected void setEndpointPlural(String endpointPlural) {
        _endpointPlural = endpointPlural;
    }

    public ObjectInfo retrieveCollectionInfo(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/getInfo", "get", ObjectInfo.class);
    }

    public Meta retrieveMeta(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/meta", "get", Meta.class);
    }

    @Required(params = {"id"})
    public SingleResponse retrieveSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint(), "get");
    }

    public ListResponse retrieveMultiple(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural(), "get", ListResponse.class);
    }

    public ArrayList<ListResponse> retrieveMultiplePaginated(RequestParams params) throws RequiredParamsException {

        ObjectInfo decoded = retrieveCollectionInfo(params);
        int count = decoded.getCount();

        params.putIfAbsent("start", "0");
        params.putIfAbsent("range", "50");

        int start = params.getAsInt("start");
        int range = params.getAsInt("range");

        ArrayList<ListResponse> data = new ArrayList<>();
        while (start < count) {
            ListResponse multi = retrieveMultiple(params);
            data.add(multi);
            start += range;
            params.put("start", String.valueOf(start));
        }
        return data;
    }

    public SingleResponse create(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural(), "post");
    }

    @Required(params = {"id"})
    public UpdateResponse update(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural(), "put", UpdateResponse.class);
    }

    public SingleResponse saveorupdate(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/saveorupdate", "post");
    }

    @Required(params = {"id"})
    public SingleResponse deleteSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint(), "delete");
    }

    public SingleResponse deleteMultiple(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural(), "delete");
    }

    protected void checkRequiredParams(RequestParams params) throws RequiredParamsException {
        try {
            String method_name = Thread.currentThread().getStackTrace()[2].getMethodName();
            Method m = getClass().getMethod(method_name, RequestParams.class);
            String[] requiredParams = m.getAnnotation(Required.class).params();

            if (objectID >= 0) {
                params.put("objectID", objectID);
            }

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
        catch (NoSuchMethodException | NullPointerException ignored) {
        }
    }
}
