package com.sonnguyen.individual.nhs.constant;

public enum SavingType {
    TERM_DEPOSIT(1,"Term Deposit"),
    DEMAND_DEPOSIT(2,"Demand Deposit"),
    UNKNOWN(0,"Unknown");
    public final int value;
    public final String label;
    SavingType(int value,String label) {
        this.label=label;
        this.value = value;
    }
    public static SavingType of(int id) {
        if(id==1) return TERM_DEPOSIT;
        if(id==2) return DEMAND_DEPOSIT;
        return UNKNOWN;
    }
}
