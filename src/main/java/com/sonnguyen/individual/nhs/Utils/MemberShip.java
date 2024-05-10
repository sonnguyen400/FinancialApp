package com.sonnguyen.individual.nhs.Utils;

public enum MemberShip {
    STANDARD("STANDARD"),GOLD("GOLD"),DIAMOND("DIAMOND"),VIP("VIP");
    public String value;
    MemberShip(String value) {
        this.value = value;
    }
}
