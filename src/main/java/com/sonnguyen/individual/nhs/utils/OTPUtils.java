package com.sonnguyen.individual.nhs.utils;

import com.sonnguyen.individual.nhs.service.EmailService;
import com.sonnguyen.individual.nhs.service.iservice.IEmailService;
import com.sonnguyen.individual.nhs.type.Otp;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static com.sonnguyen.individual.nhs.utils.Constants.OTP;
@Model
public class OTPUtils {
    @Inject
    EmailService emailService;
    @Inject
    IEmailService iEmailService;
    Otp otp;
    public OTPUtils generateOTP() {
        otp= Otp.generator(6,15);
        System.out.println("Debug: OTP="+otp);
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
        iEmailService.sendEmail(dest,otp.getCode(),
                "This is verification code sent from System! Please don't share this email with anyone").thenRun(()->{
        });
        return this;
    }
    public static boolean isValid(HttpServletRequest request){
        Otp otp1= (Otp) SessionUtils.getSession(request,OTP);
        return otp1 != null && otp1.getCode().equals(request.getParameter(OTP)) && Otp.isExpired(otp1);
    }
}
