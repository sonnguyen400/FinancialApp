package com.sonnguyen.individual.nhs.constant;

public enum AccountType {
    PRINCIPAL("PRINCIPAL"),
    SAVINGS("SAVING"),
    GENERAL("GENERAL");
    public String value;
    AccountType(String value) {
        this.value = value;
    }
}
