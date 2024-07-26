package com.sonnguyen.individual.nhs.exception;

public class CommitTransactionException extends RuntimeException{
    public CommitTransactionException() {
        super();
    }

    public CommitTransactionException(String message) {
        super(message);
    }

    public CommitTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommitTransactionException(Throwable cause) {
        super(cause);
    }

    protected CommitTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
