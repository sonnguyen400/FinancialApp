package com.sonnguyen.individual.nhs.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.net.InetAddress;
import java.net.URL;
import java.util.Properties;

public class EmailService {
    private static final String email="hellohoangson@gmail.com";
    private static final String password="HoangSon2003";
    private static final   Properties properties=new Properties();
    private static final Authenticator authenticator;
    private static final Session session;

    static{
        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "587"); //TLS Port
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        authenticator=new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password);
            }
        };
        session= Session.getInstance(properties,authenticator);
    }

    public static void sendEmail(String dest,String content, String subject) throws MessagingException {
        BodyPart bodyPart=new MimeBodyPart();
        bodyPart.setHeader("From",subject);

    }
}
