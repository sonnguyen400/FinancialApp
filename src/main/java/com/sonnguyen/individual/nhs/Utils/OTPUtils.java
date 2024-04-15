package com.sonnguyen.individual.nhs.Utils;

import com.sonnguyen.individual.nhs.Service.EmailService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static com.sonnguyen.individual.nhs.Utils.Constants.OTP;
@Model
public class OTPUtils {
    @Inject
    EmailService emailService;
    String otp;
    public OTPUtils generateOTP() {
        otp= UUID.randomUUID().toString().substring(0,6);
        return this;
    }
    public OTPUtils sessionSave(HttpServletRequest request){
        request.getSession().setAttribute(OTP,otp);
        return this;
    }
    public String sessionSave(HttpServletRequest request,String otp){
        request.getSession().setAttribute(OTP,otp);
        return otp;
    }
    public OTPUtils sendToEmail(String dest){
        try {
            emailService.sendEmail(dest,otp,otp);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
