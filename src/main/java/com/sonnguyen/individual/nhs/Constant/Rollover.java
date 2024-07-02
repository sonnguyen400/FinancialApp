package com.sonnguyen.individual.nhs.Constant;

public enum Rollover {
    WITHDRAW_ENTIRE(1),
    ROLLOVER_PRINCIPAL(2),
    ROLLOVER_ALL(3);
    public final int value;
    Rollover(int value) {
        this.value = value;
    }
}
