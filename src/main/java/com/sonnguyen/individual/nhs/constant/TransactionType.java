package com.sonnguyen.individual.nhs.constant;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    TRANSFER("TRANSFER"),
    DISBURSEMENT("DISBURSEMENT"),
    PAYOFF("PAYOFF");
    public final String value;
    TransactionType(String value) {
        this.value = value;
    }
}