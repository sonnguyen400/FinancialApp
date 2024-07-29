package com.sonnguyen.individual.nhs.constant;

public enum AccountStatus {
    OPEN(1),
    INACTIVE(2),
    CLOSED(3),
    OVERDRAWN(4),
    FROZEN(5),
    PENDING(6);

    public final int value;
    AccountStatus(int value) {
        this.value=value;
    }
}
