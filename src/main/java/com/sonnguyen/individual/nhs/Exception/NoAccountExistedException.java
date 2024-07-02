package com.sonnguyen.individual.nhs.Exception;

public class NoAccountExistedException extends RuntimeException{
    public NoAccountExistedException() {
        super();
    }

    public NoAccountExistedException(String message) {
        super(message);
    }

    public NoAccountExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAccountExistedException(Throwable cause) {
        super(cause);
    }

    protected NoAccountExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
