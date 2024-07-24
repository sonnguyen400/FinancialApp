package com.sonnguyen.individual.nhs.type;

public class Message {
    public Type type;
    public String message;
    public String  href;
    public String link;
    public enum Type {
        ERROR,SUCCESS,WARNING,INFO
    }

    public Message() {
    }

    public Message(Type type, String message, String href, String link) {
        this.type = type;
        this.message = message;
        this.href = href;
        this.link = link;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
