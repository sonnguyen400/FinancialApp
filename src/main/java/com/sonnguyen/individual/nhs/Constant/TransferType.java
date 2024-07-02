package com.sonnguyen.individual.nhs.Constant;

public enum TransferType {
    SEND(1),
    RECEIVE(2);
    public final int value;
    TransferType(int id) {
        this.value=id;
    }
}
