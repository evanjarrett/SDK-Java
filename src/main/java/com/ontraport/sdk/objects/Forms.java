package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

public class Forms extends AbstractObject {

    protected String _endpoint = "Form";
    protected String _endpointPlural = "Forms";

    public Forms(Ontraport client) {
        super(client);
    }

    @Required(params = {"id"})
    public String retrieveSmartFormHTML(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint.toLowerCase(), "get");
    }
}
