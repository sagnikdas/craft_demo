package com.sagnikdas.intuit.demo.error;

public class EmptyVinIdsException extends Exception{
    public EmptyVinIdsException() {
    }

    public EmptyVinIdsException(String message) {
        super(message);
    }

    public EmptyVinIdsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyVinIdsException(Throwable cause) {
        super(cause);
    }

    public EmptyVinIdsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
