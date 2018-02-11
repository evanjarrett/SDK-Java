package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;
import com.ontraport.sdk.objects.response.Meta;

import java.util.HashMap;
import java.util.Map;

public class Objects extends AbstractObject {

    protected String _endpoint = "object";
    protected String _endpointPlural = "objects";

    public Objects(Ontraport client) {
        super(client);
    }

    @Override
    @Required(params = {"objectID"})
    public String retrieveCollectionInfo(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/getInfo", "get");
    }

    @Override
    public String retrieveMeta(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/meta", "get");
    }

    @Override
    @Required(params = {"objectID", "id"})
    public String retrieveSingle(RequestParams params) throws RequiredParamsException {
        return super.retrieveSingle(params);
    }

    @Override
    @Required(params = {"objectID"})
    public String retrieveMultiple(RequestParams params) throws RequiredParamsException {
        return super.retrieveMultiple(params);
    }

    @Override
    public String retrieveMultiplePaginated(RequestParams params) throws RequiredParamsException {
        return super.retrieveMultiplePaginated(params);
    }

    @Override
    @Required(params = {"objectID"})
    public String create(RequestParams params) throws RequiredParamsException {
        return super.create(params);
    }

    @Override
    @Required(params = {"objectID"})
    public String update(RequestParams params) throws RequiredParamsException {
        return super.update(params);
    }

    @Override
    @Required(params = {"objectID"})
    public String saveorupdate(RequestParams params) throws RequiredParamsException {
        return super.saveorupdate(params);
    }

    @Override
    @Required(params = {"objectID", "id"})
    public String deleteSingle(RequestParams params) throws RequiredParamsException {
        return super.deleteSingle(params);
    }

    @Override
    @Required(params = {"objectID"})
    public String deleteMultiple(RequestParams params) throws RequiredParamsException {
        return super.deleteMultiple(params);
    }

    public String retrieveCustomObjects(RequestParams params) throws RequiredParamsException {

        String multi = retrieveMeta(params);
        Meta meta = gson.fromJson(multi, Meta.class);
        Map<Integer, Meta.Data> custom_objects = new HashMap<>();
        for (Map.Entry<String, Meta.Data> entry : meta.getData().entrySet()) {
            Integer id = Integer.valueOf(entry.getKey());
            if (id >= 10000) {
                custom_objects.put(id, entry.getValue());
            }
        }
        return gson.toJson(custom_objects);
    }

    @Required(params = {"objectID"})
    public String retrieveAllWithTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/tag", "get");
    }

    @Required(params = {"objectID", "email"})
    public String retrieveIdByEmail(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/getByEmail", "get");
    }

    @Required(params = {"ids"})
    public String pause(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/pause", "post");
    }

    @Required(params = {"ids"})
    public String unpause(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/unpause", "post");
    }

    @Required(params = {"ids", "add_list"})
    public String addToSequence(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/sequence", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String removeFromSequence(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/sequence", "delete");
    }

    @Required(params = {"ids", "add_list"})
    public String addTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/tag", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String removeTag(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/tag", "delete");
    }

    @Required(params = {"ids", "add_names"})
    public String addTagByName(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/tagByName", "post");
    }

    @Required(params = {"ids", "remove_names"})
    public String removeTagByName(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/tagByName", "delete");
    }

    @Required(params = {"ids", "add_list"})
    public String subscribe(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/subscribe", "post");
    }

    @Required(params = {"ids", "remove_list"})
    public String unsubscribe(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpointPlural + "/subscribe", "delete");
    }
}
