package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

public class Messages extends AbstractObject {

    protected String _endpoint = "Message";
    protected String _endpointPlural = "Messages";

    public Messages(Ontraport client) {
        super(client);
    }

    @Required(params = {"type"})
    public String create(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase(), "post");
    }

    @Required(params = {"id", "type"})
    public String deleteSingle(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase(), "put");
    }
}
