package com.sonnguyen.individual.nhs.Constant;

public enum AccountStatus {
    OPEN("OPEN"),
    INACTIVE("INACTIVE"),
    CLOSED("CLOSED"),
    OVERDRAWN("OVERDRAWN"),
    FROZEN("FROZEN"),
    PENDING("PENDING");

    public final String value;
    AccountStatus(String value) {
        this.value=value;
    }
}
