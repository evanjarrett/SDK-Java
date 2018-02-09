package com.ontraport.sdk.exceptions;

public class OntraportAPIException extends Exception {
    public OntraportAPIException(String method) {
        super(method);
    }
}
