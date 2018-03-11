package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;

public class CustomObjects extends Objects {

    public CustomObjects(Ontraport client, int objectID) {
        super(client);
        this.objectID = objectID;
    }

}
