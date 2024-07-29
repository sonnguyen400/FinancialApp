package com.sonnguyen.individual.nhs.constant;

public enum AccountType {
    PRIMARY(1),
    SAVINGS(2),
    INCORPORATE(3),
    PRINCIPAL(4);
    public final int value;
    AccountType(int value) {
        this.value = value;
    }
}
