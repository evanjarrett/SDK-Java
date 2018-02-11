package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

public class LandingPages extends AbstractObject {

    protected String _endpoint = "LandingPage";
    protected String _endpointPlural = "LandingPages";

    public LandingPages(Ontraport client) {
        super(client);
    }

    @Required(params = {"id"})
    public String getHostedURL(RequestParams params) throws RequiredParamsException {
        checkRequiredParams(params);
        return client.request(params, "landingPage/getHostedURL", "get");
    }
}
