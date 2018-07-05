package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;

public class Forms extends AbstractObject {

    protected String _endpoint = "Form";
    protected String _endpointPlural = "Forms";

    public Forms(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

    @Required(params = {"id"})
    public SingleResponse retrieveSmartFormHTML(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return _client.request(params, getEndpoint().toLowerCase(), "get");
    }
}
