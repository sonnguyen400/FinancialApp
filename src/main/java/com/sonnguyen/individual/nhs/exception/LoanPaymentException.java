package com.sonnguyen.individual.nhs.exception;

/**
 * This exception triggered when someone try to pay a monthly loan payment
 * - causal
 *      - Monthly payment has been paid
 *      - Loan's is fully paid and has finished before
 */
public class LoanPaymentException extends RuntimeException{
    public LoanPaymentException() {
        super();
    }

    public LoanPaymentException(String message) {
        super(message);
    }

    public LoanPaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanPaymentException(Throwable cause) {
        super(cause);
    }

    protected LoanPaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
