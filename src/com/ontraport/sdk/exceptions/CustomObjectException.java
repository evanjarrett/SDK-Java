package com.ontraport.sdk.exceptions;

public class CustomObjectException extends OntraportAPIException {
    public CustomObjectException(String method) {
        super(method);
    }
}
