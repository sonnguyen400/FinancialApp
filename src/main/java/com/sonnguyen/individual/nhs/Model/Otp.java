package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;

    @Column(name = "expired_time")
    private Date expiredTime;
    @Column(name = "transaction_id")
    private int transactionId;

    public static Otp generator(int transactionId){
        Otp otp=new Otp();
        otp.setTransactionId(transactionId);
        otp.setCode(String.valueOf(UUID.randomUUID().hashCode()).substring(0,6));
        otp.setExpiredTime(new Date());
        return otp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
