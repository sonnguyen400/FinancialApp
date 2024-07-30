package com.sonnguyen.individual.nhs.dto;


import org.apache.http.HttpStatus;

public class Result extends Message{
    private int httpStatus;

    public Result(int httpStatus) {
        this.httpStatus = httpStatus;
    }

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
