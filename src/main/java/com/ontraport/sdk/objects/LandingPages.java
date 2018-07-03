package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.SingleResponse;
import com.ontraport.sdk.http.RequestParams;
import com.ontraport.sdk.http.Required;

public class LandingPages extends AbstractObject {

    protected String _endpoint = "LandingPage";
    protected String _endpointPlural = "LandingPages";

    public LandingPages(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

    @Required(params = {"id"})
    public SingleResponse getHostedURL(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return _client.request(params, "landingPage/getHostedURL", "get");
    }
}
