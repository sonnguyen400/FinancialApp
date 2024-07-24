package com.sonnguyen.individual.nhs.Constant;

public enum Rollover {
    WITHDRAW_ENTIRE(1,"Withdraw Entire"),
    ROLLOVER_PRINCIPAL(2,"Rollover Principal"),
    ROLLOVER_ALL(3,"Rollover All"),
    UNKNOWN(4,"UNKNOWN");
    public final int value;
    public final String label;
    Rollover(int value,String label) {
        this.value = value;
        this.label = label;
    }
    public static Rollover of(Integer id){
        if(id==null) return null;
        if(id.equals(1))   return WITHDRAW_ENTIRE;
        if(id.equals(2))   return ROLLOVER_PRINCIPAL;
        if(id.equals(3))   return ROLLOVER_ALL;
        return UNKNOWN;
    }
}
