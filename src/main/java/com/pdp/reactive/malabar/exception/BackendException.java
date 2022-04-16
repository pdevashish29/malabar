package com.pdp.reactive.malabar.exception;

public class BackendException extends Exception{

    public BackendException() {
    }

    public BackendException(String message) {
        super(message);
    }

    public BackendException(String message, Throwable cause) {
        super(message, cause);
    }

    public BackendException(Throwable cause) {
        super(cause);
    }

    public BackendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
