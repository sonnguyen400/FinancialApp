package com.sonnguyen.individual.nhs.exception;

public class EntityIntegrityException extends RuntimeException{
    public EntityIntegrityException() {
        super();
    }

    public EntityIntegrityException(String message) {
        super(message);
    }

    public EntityIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIntegrityException(Throwable cause) {
        super(cause);
    }

    protected EntityIntegrityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
