package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

public class CreditCards extends AbstractObject {

    protected String _endpoint = "CreditCard";
    protected String _endpointPlural = "CreditCards";

    public CreditCards(Ontraport client) {
        super(client);
    }

    @Required(params = {"id"})
    public String setDefault(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, _endpoint + "/default", "put");
    }

}
