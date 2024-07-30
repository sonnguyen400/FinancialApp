package com.sonnguyen.individual.nhs.dto;

import javax.persistence.Column;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Otp {
    private String code;

    @Column(name = "expired_time")
    private Date expiredTime;

    public static Otp generator(int length,int expiration){
        Otp otp=new Otp();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,expiration);
        otp.setCode(String.valueOf(UUID.randomUUID().hashCode()).substring(0,length));
        otp.setExpiredTime(calendar.getTime());
        return otp;
    }

    public static boolean isExpired(Otp otp){
        Calendar calendar=Calendar.getInstance();
        Calendar expiration=Calendar.getInstance();
        expiration.setTime(otp.getExpiredTime());
        return calendar.before(expiration);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "Otp{" +
                "code='" + code + '\'' +
                ", expiredTime=" + expiredTime +
                '}';
    }
}
