package com.sonnguyen.individual.nhs.Utils;

public enum Route {
    LOGIN("/login","Login"),
    REGISTER("/register","register"),
    HOME("/app/home","home"),
    BALANCE_FLUCTUATIONS("/app/fluctuation","balance_fluc"),
    TRANSFER("/app/transfer","transfer"),
    LOAN_MANAGE("/app/loan","loan_manage"),
    LOAN_CREATE("/app/loan/create","loan_create");

    public String value;
    public String name;
    Route(String value,String name) {
        this.value = value;
        this.name=name;
    }
}
