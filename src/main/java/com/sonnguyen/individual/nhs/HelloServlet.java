package com.sonnguyen.individual.nhs;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.AccountRepository;
import com.sonnguyen.individual.nhs.Service.EmailService;

import javax.mail.MessagingException;
import java.util.Optional;

public class HelloServlet {
    public static void main(String[] args){
        EmailService emailService=new EmailService();
        try {
            emailService.sendEmail("hellohoangson@gmail.com","","");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}