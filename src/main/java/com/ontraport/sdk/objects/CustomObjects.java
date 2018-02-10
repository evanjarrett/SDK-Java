package com.ontraport.sdk.objects;

import com.ontraport.sdk.Ontraport;
import com.ontraport.sdk.exceptions.RequiredParamsException;
import com.ontraport.sdk.http.Required;

import java.util.Map;

public class CustomObjects extends Objects {

    public CustomObjects(Ontraport client) {
        super(client);
    }

    @Override
    @Required(params={"id"})
    public String retrieveSingle(Map<String, String> requestParams) throws RequiredParamsException {
        super.retrieveSingle(requestParams);
        return "";
    }

}
