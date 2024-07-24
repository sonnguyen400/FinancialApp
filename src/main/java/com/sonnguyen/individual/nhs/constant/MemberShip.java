package com.sonnguyen.individual.nhs.Constant;

public enum MemberShip {
    STANDARD(1),
    GOLD(2),
    DIAMOND(3),
    VIP(4);
    public final int value;
    MemberShip(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
