package com.sonnguyen.individual.nhs.Constant;

public enum MemberShip {
    STANDARD("STANDARD"),GOLD("GOLD"),DIAMOND("DIAMOND"),VIP("VIP");
    public final String value;
    MemberShip(String value) {
        this.value = value;
    }
}
