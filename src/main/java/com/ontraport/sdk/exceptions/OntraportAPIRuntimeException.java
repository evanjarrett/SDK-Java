package com.ontraport.sdk.exceptions;

public class OntraportAPIRuntimeException extends RuntimeException {
    public OntraportAPIRuntimeException() {
        super();
    }

    public OntraportAPIRuntimeException(String message) {
        super(message);
    }
}
