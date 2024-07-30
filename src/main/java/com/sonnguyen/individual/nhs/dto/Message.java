package com.sonnguyen.individual.nhs.dto;

public class Message {
    protected Type type;
    protected String message;

    public enum Type {
        ERROR,SUCCESS,WARNING,INFO
    }


    public Message() {
    }

    public Message(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
