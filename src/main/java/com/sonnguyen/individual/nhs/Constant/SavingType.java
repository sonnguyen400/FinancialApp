package com.sonnguyen.individual.nhs.Constant;

public enum SavingType {
    DEMAND_DEPOSIT("DEMAND_DEPOSIT"),
    TERM_DEPOSIT("TERM_DEPOSIT");
    public final String value;
    SavingType(String value) {
        this.value = value;
    }
}
