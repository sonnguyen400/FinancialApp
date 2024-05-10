package com.sonnguyen.individual.nhs.Utils;

public enum AccountType {
    PRINCIPAL("PRINCIPAL"),
    SAVINGS("SAVINGS"),
    GENERAL("GENERAL");
    public String value;
    AccountType(String value) {
        this.value = value;
    }
}
