package com.sonnguyen.individual.nhs.Constant;

public enum TransactionType {
    DEPOSIT(1),
    WITHDRAWAL(2),
    TRANSFER(3),
    RECEIVE(4),
    DISBURSEMENT(5),
    PAYOFF(6);
    public final int value;
    TransactionType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
