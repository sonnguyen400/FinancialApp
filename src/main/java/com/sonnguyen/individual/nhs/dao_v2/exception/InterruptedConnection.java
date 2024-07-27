package com.sonnguyen.individual.nhs.dao_v2.exception;

public class InterruptedConnection extends RuntimeException{
    public InterruptedConnection() {
        super();
    }

    public InterruptedConnection(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptedConnection(String message) {
        super(message);
    }

    public InterruptedConnection(Throwable cause) {
        super(cause);
    }

    protected InterruptedConnection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
