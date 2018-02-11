package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;

public class Transactions extends AbstractObject {

    protected String _endpoint = "Transaction";
    protected String _endpointPlural = "Transactions";

    public Transactions(Ontraport client) {
        super(client);
    }

}
