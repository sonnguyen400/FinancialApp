package com.sonnguyen.individual.nhs.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sonnguyen.individual.nhs.context.annotation.Value;
import com.sonnguyen.individual.nhs.service.iservice.IEmailService;

import javax.enterprise.inject.Model;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
@Model
public class SendGridEmailService implements IEmailService {
    @Value(name = "service.mail.sendgrid.apiKey")
    private String apiKey;
    @Value(name = "service.mail.sendgrid.from")
    private String fromAddress;
    @Override
    public CompletableFuture<Void> sendEmail(String dest, String content, String subject) {
        Email from=new Email(fromAddress);
        return CompletableFuture.runAsync(()->{
            Email to=new Email(dest);
            Content content1=new Content("text/plain", content);
            Mail mail=new Mail(from,subject,to,content1);
            SendGrid sendGrid=new SendGrid(apiKey);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sendGrid.api(request);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
