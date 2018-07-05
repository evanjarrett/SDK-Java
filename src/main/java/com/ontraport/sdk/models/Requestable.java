package com.ontraport.sdk.models;

import com.ontraport.sdk.http.RequestParams;

public interface Requestable {

    //Requestable fromResponse(SingleResponse response);

    RequestParams toRequestParams();
}
