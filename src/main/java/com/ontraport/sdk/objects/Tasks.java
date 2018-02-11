package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;

public class Tasks extends AbstractObject {

    protected String _endpoint = "Task";
    protected String _endpointPlural = "Tasks";

    public Tasks(Ontraport client) {
        super(client);
    }


    public String assign(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase() + "/assign", "get");
    }

    public String cancel(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase() + "/cancel", "get");
    }

    public String complete(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase() + "/complete", "get");
    }

    public String reschedule(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase() + "/reschedule", "get");
    }


}
