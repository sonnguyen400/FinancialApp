package com.sonnguyen.individual.nhs.Service;

import javax.enterprise.inject.Model;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
@Model
public class EmailService {
    private static final String email="nhsappbuilding@personal.com";
    private static final String password="979761189A8E7712024E01CE29E7F9BDFE3F";
    private static final   Properties properties=new Properties();
    private static final Authenticator authenticator;
    private static final Session session;

    static{
        properties.put("mail.smtp.host", "smtp.elasticemail.com"); //SMTP Host
        properties.put("mail.smtp.port", "2525"); //TLS Port
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

    public void sendEmail(String dest,String content, String subject) throws MessagingException {
        BodyPart bodyPart=new MimeBodyPart();
        bodyPart.setText(content);
        Multipart multipart=new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(content, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest, false));
            System.out.println("Message is ready");
            Transport.send(msg);
            System.out.println("Message was sent");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
