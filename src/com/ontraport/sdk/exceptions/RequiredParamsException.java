package com.ontraport.sdk.exceptions;

public class RequiredParamsException extends OntraportAPIException {
    public RequiredParamsException(String method) {
        super(method);
    }
}
