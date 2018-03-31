package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.CustomObjectResponse;
import com.ontraport.sdk.http.RequestParams;

public class CustomObjectsMeta extends AbstractObject {

    public CustomObjectsMeta(Ontraport client) {
        super(client, "CustomObject", "CustomObjects");
    }

    public CustomObjectResponse meta(RequestParams params) throws RequiredParamsException {
        this.checkRequiredParams(params);
        return this.client.request(params, this.getEndpointPlural(), "get", CustomObjectResponse.class);
    }
}
