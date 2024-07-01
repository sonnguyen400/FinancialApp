package com.sonnguyen.individual.nhs.constant;

public enum RequestFlags {
    CREATE_TRANSFER("CREATE_TRANSFER"),
    CREATE_LOAN("CREATE_LOAN"),
    CREATE_SAVING("CREATE_SAVING"),
    CREATE_PAYMENT("CREATE_PAYMENT");
    public final String value;
    RequestFlags(String value) {
        this.value = value;
    }
}
