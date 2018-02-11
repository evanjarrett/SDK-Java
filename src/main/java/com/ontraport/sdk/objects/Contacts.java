package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;

public class Contacts extends AbstractObject {

    protected String _endpoint = "Contact";
    protected String _endpointPlural = "Contacts";

    public Contacts(Ontraport client) {
        super(client);
    }

}
