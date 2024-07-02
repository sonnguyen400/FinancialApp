package com.sonnguyen.individual.nhs.Constant;

public enum SavingType {
    TERM_DEPOSIT(1),
    DEMAND_DEPOSIT(2);
    public final int value;
    SavingType(int value) {
        this.value = value;
    }
}
