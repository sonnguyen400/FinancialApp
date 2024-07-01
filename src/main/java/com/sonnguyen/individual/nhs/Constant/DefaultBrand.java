package com.sonnguyen.individual.nhs.Constant;

public enum DefaultBrand {
    ID(3);
    final int value;
    DefaultBrand(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
