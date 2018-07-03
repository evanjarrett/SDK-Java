package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;

public class CreditCards extends AbstractObject {

    protected String _endpoint = "CreditCard";
    protected String _endpointPlural = "CreditCards";

    public CreditCards(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

    @Required(params = {"id"})
    public SingleResponse setDefault(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return _client.request(params, getEndpoint() + "/default", "put");
    }

}
