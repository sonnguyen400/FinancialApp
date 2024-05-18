package com.sonnguyen.individual.nhs.Constant;

public enum Rollover {
    WITHDRAW_ENTIRE("WITHDRAW_ENTIRE"),
    ROLLOVER_PRINCIPAL("ROLLOVER_PRINCIPAL"),
    ROLLOVER_ALL("ROLLOVER_INTEREST");
    public final String value;
    Rollover(String value) {
        this.value = value;
    }
}
