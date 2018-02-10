package com.ontraport.sdk.exceptions;

public class HttpMethodException extends OntraportAPIException {
    public HttpMethodException(String method) {
        super(method);
    }
}
