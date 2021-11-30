package com.sagnikdas.intuit.demo.error;

public class VinNotFoundException extends Exception{

    public VinNotFoundException() {
    }

    public VinNotFoundException(String message) {
        super(message);
    }

    public VinNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VinNotFoundException(Throwable cause) {
        super(cause);
    }

    public VinNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
