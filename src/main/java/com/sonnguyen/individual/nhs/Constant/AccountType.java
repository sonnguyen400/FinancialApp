package com.sonnguyen.individual.nhs.Constant;

public enum AccountType {
    PRINCIPAL("PRINCIPAL"),
    SAVINGS("SAVING"),
    GENERAL("GENERAL");
    public String value;
    AccountType(String value) {
        this.value = value;
    }
}
