package com.sonnguyen.individual.nhs.constant;

public enum LoanStatus {
    PENDING(2),
    APPROVED(1),
    REJECTED(0);
    public final int value;
    LoanStatus(int value) {
        this.value = value;
    }
}
