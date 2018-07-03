package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;

public class Messages extends AbstractObject {

    protected String _endpoint = "Message";
    protected String _endpointPlural = "Messages";

    public Messages(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

    @Required(params = {"type"})
    public SingleResponse create(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return _client.request(params, getEndpoint().toLowerCase(), "post");
    }

    @Required(params = {"id", "type"})
    public SingleResponse deleteSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return _client.request(params, getEndpoint().toLowerCase(), "put");
    }
}
