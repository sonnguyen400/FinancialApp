package com.sonnguyen.individual.nhs.Constant;

public enum TransactionStatus {
    SUCCESS(1),
    PENDING(2),
    FAILURE(0);
    public final int value;
    TransactionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
