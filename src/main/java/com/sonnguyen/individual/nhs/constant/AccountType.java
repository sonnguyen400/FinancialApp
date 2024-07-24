package com.sonnguyen.individual.nhs.Constant;

public enum AccountType {
    PRINCIPAL(1),
    SAVINGS(2),
    GENERAL(3);
    public final int value;
    AccountType(int value) {
        this.value = value;
    }
}
