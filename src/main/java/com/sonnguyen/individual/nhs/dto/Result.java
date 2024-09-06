package com.sonnguyen.individual.nhs.dto;


public class Result extends Message{
    private int httpStatus;

    public Result(){

    }
    public Result(Type type, String message, int httpStatus) {
        super(type, message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
