package com.sonnguyen.individual.nhs.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sonnguyen.individual.nhs.service.iService.IEmailService;

import javax.enterprise.inject.Model;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
@Model
public class SendGridEmailService implements IEmailService {
    private final Email from=new Email("hellohoangson@gmail.com");
    @Override
    public CompletableFuture<Void> sendEmail(String dest, String content, String subject) {
        return CompletableFuture.runAsync(()->{
            Email to=new Email(dest);
            Content content1=new Content("text/plain", content);
            Mail mail=new Mail(from,subject,to,content1);
            SendGrid sendGrid=new SendGrid("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");
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
