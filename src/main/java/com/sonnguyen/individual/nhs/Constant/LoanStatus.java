package com.sonnguyen.individual.nhs.Constant;

public enum LoanStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");
    public final String value;
    LoanStatus(String value) {
        this.value = value;
    }
}
