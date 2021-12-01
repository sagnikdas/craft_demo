package com.sagnikdas.intuit.demo.error;

public class InvalidSearchTypeException extends Exception{
    public InvalidSearchTypeException() {
    }

    public InvalidSearchTypeException(String message) {
        super(message);
    }

    public InvalidSearchTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSearchTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidSearchTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
