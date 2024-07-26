package com.sonnguyen.individual.nhs.exception;

public class FailureTransaction extends RuntimeException{
    public FailureTransaction() {
        super();
    }

    public FailureTransaction(String message) {
        super(message);
    }

    public FailureTransaction(String message, Throwable cause) {
        super(message, cause);
    }

    public FailureTransaction(Throwable cause) {
        super(cause);
    }

    protected FailureTransaction(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
