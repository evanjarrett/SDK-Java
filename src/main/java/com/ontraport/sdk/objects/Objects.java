package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.ListResponse;
import com.ontraport.sdk.http.MessageResponse;
import com.ontraport.sdk.http.Meta;
import com.ontraport.sdk.http.ObjectInfo;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.UpdateResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Objects extends AbstractObject {

    protected String _endpoint = "object";
    protected String _endpointPlural = "objects";

    public Objects(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

    @Override
    @Required(params = {"objectID"})
    public ObjectInfo retrieveCollectionInfo(RequestParams params) throws RequiredParamsException {
        return super.retrieveCollectionInfo(params);
    }

    @Override
    @Required(params = {"objectID", "id"})
    public SingleResponse retrieveSingle(RequestParams params) throws RequiredParamsException {
        return super.retrieveSingle(params);
    }

    @Override
    @Required(params = {"objectID"})
    public ListResponse retrieveMultiple(RequestParams params) throws RequiredParamsException {
        return super.retrieveMultiple(params);
    }

    @Override
    public ArrayList<ListResponse> retrieveMultiplePaginated(RequestParams params) throws RequiredParamsException {
        return super.retrieveMultiplePaginated(params);
    }

    @Override
    @Required(params = {"objectID"})
    public SingleResponse create(RequestParams params) throws RequiredParamsException {
        return super.create(params);
    }

    @Override
    @Required(params = {"objectID"})
    public UpdateResponse update(RequestParams params) throws RequiredParamsException {
        return super.update(params);
    }

    @Override
    @Required(params = {"objectID"})
    public SingleResponse saveorupdate(RequestParams params) throws RequiredParamsException {
        return super.saveorupdate(params);
    }

    @Override
    @Required(params = {"objectID", "id"})
    public SingleResponse deleteSingle(RequestParams params) throws RequiredParamsException {
        return super.deleteSingle(params);
    }

    @Override
    @Required(params = {"objectID"})
    public SingleResponse deleteMultiple(RequestParams params) throws RequiredParamsException {
        return super.deleteMultiple(params);
    }

    public Map<Integer, Meta.Data> retrieveCustomObjects(RequestParams params) throws RequiredParamsException {

        Meta meta = retrieveMeta(params);
        Map<Integer, Meta.Data> custom_objects = new HashMap<>();
        for (Map.Entry<String, Meta.Data> entry : meta.getData().entrySet()) {
            Integer id = Integer.valueOf(entry.getKey());
            if (id >= 10000) {
                custom_objects.put(id, entry.getValue());
            }
        }
        return custom_objects;
    }

    @Required(params = {"objectID"})
    public ListResponse retrieveAllWithTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/tag", "get", ListResponse.class);
    }

    @Required(params = {"objectID", "email"})
    public SingleResponse retrieveIdByEmail(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/getByEmail", "get");
    }

    @Required(params = {"ids"})
    public SingleResponse pause(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/pause", "post");
    }

    @Required(params = {"ids"})
    public SingleResponse unpause(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/unpause", "post");
    }

    @Required(params = {"ids", "add_list"})
    public SingleResponse addToSequence(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/sequence", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public SingleResponse removeFromSequence(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/sequence", "delete");
    }

    @Required(params = {"ids", "add_list"})
    public SingleResponse addTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/tag", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public SingleResponse removeTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/tag", "delete");
    }

    @Required(params = {"ids", "add_names"})
    public MessageResponse addTagByName(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/tagByName", "put", MessageResponse.class);
    }

    @Required(params = {"ids", "remove_names"})
    public MessageResponse removeTagByName(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/tagByName", "delete", MessageResponse.class);
    }

    @Required(params = {"ids", "add_list"})
    public MessageResponse subscribe(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/subscribe", "post", MessageResponse.class);
    }

    @Required(params = {"ids", "remove_list"})
    public MessageResponse unsubscribe(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpointPlural() + "/subscribe", "delete", MessageResponse.class);
    }
}
