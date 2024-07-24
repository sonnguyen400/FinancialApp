package com.sonnguyen.individual.nhs.Constant;

public enum AccountTier {
    SILVER(1,"SILVER"),
    GOLD(2,"GOLD"),
    DIAMOND(2,"DIAMOND");
    public final int id;
    public final String name;
    AccountTier(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
