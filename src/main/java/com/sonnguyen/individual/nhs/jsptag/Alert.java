package com.sonnguyen.individual.nhs.jsptag;

import com.sonnguyen.individual.nhs.dto.Message;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class Alert extends SimpleTagSupport {
    private String type;
    private String href;
    private String link;
    StringWriter sw = new StringWriter();
    public void setType(String type) {
        this.type = type;
    }
    public void setHref(String href) {
        this.href=href;
    }
    public void setLink(String link) {
        this.link=link;
    }
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out=getJspContext().getOut();
        getJspBody().invoke(sw);
        String message=sw.toString();
        Message.Type messageType;
        if(type==null) messageType= Message.Type.INFO;
        else {
            try{
                messageType=Message.Type.valueOf(type);
            }catch (IllegalArgumentException e){
                messageType= Message.Type.WARNING;
            }
        }
        if(messageType==Message.Type.ERROR) out.println("<div class='alert alert-danger'><div class=\"d-flex\"><div class=\"flex-grow-1\">"+message+"</div><a class=\"alert-link\" href="+href+">"+link+"</a></div></div>");
        else if(messageType== Message.Type.SUCCESS)  out.println("<div class='alert alert-success'><div class=\"d-flex\"><div class=\"flex-grow-1\">"+message+"</div><a class=\"alert-link\" href="+href+">"+link+"</a></div></div>");
        else if(messageType== Message.Type.WARNING)  out.println("<div class='alert alert-warning'><div class=\"d-flex\"><div class=\"flex-grow-1\">"+message+"</div><a class=\"alert-link\" href="+href+">"+link+"</a></div></div>");
        else if(messageType== Message.Type.INFO)  out.println("<div class='alert alert-info'><div class=\"d-flex\"><div class=\"flex-grow-1\">"+message+"</div><a class=\"alert-link\" href="+href+">"+link+"</a></div></div>");
    }
}
