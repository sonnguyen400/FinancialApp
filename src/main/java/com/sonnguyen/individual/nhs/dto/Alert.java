package com.sonnguyen.individual.nhs.dto;

public class Alert extends Message{
    private String  href;
    private String link;

    public Alert(){

    }
    public Alert(String href, String link) {
        this.href = href;
        this.link = link;
    }


    public Alert(Type type, String message) {
        super(type, message);
    }

    public Alert(Type type, String message, String href, String link) {
        super(type, message);
        this.href = href;
        this.link = link;
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
}
