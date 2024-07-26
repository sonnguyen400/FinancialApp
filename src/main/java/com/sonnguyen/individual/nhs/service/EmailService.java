package com.sonnguyen.individual.nhs.service;


import javax.enterprise.inject.Model;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Model
public class EmailService {
    private static final String email="stu715105211@hnue.edu.vn";
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

    public CompletableFuture<Void> sendEmail(String dest,String content, String subject) {
        return CompletableFuture.runAsync(()->{
            try {
                BodyPart bodyPart=new MimeBodyPart();
                bodyPart.setText(content);
                Multipart multipart=new MimeMultipart();
                multipart.addBodyPart(bodyPart);
                MimeMessage msg = new MimeMessage(session);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");

                msg.setFrom(new InternetAddress("nhsdev@example.com", "No-reply_HarmonyBank(Dev_)"));

                msg.setReplyTo(InternetAddress.parse("stu715105211@hnue.edu.vn", false));
                msg.setSubject(subject, "UTF-8");
                msg.setText(content, "UTF-8");
                msg.setSentDate(new Date());

                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest, false));
                Transport.send(msg);
            } catch (UnsupportedEncodingException | MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
