package com.sonnguyen.individual.nhs.constant;

public enum Rollover {
    WITHDRAW_ENTIRE("WITHDRAW_ENTIRE"),
    ROLLOVER_PRINCIPAL("ROLLOVER_PRINCIPAL"),
    ROLLOVER_ALL("ROLLOVER_ALL");
    public final String value;
    Rollover(String value) {
        this.value = value;
    }
}
