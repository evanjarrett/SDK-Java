package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;

public class CustomObjects extends AbstractObject {

    protected String _endpoint = "CustomObject";
    protected String _endpointPlural = "CustomObjects";

    public CustomObjects(Ontraport client) {
        super(client);
        setEndpoint(_endpoint);
        setEndpointPlural(_endpointPlural);
    }

}
