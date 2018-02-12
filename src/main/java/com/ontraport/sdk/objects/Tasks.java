package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.RequestParams;

public class Tasks extends AbstractObject {

    protected String _endpoint = "Task";
    protected String _endpointPlural = "Tasks";

    public Tasks(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }


    public SingleResponse assign(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint().toLowerCase() + "/assign", "get");
    }

    public SingleResponse cancel(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint().toLowerCase() + "/cancel", "get");
    }

    public SingleResponse complete(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint().toLowerCase() + "/complete", "get");
    }

    public SingleResponse reschedule(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, getEndpoint().toLowerCase() + "/reschedule", "get");
    }


}
