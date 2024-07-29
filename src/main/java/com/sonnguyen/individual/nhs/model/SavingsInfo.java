package com.sonnguyen.individual.nhs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Table(name = "savings_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsInfo {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Column(name = "type")
    private Integer type;
    @Column(name = "rollover")
    private Integer rollover;
    @Column(name = "interest_rate")
    @NotNull
    private BigDecimal interestRate;
    @Column(name = "term")
    @NotNull
    private Integer term;
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "update_at",insertable = false)
    private Date updateAt;

    @Column
    private int beneficiary_account_id;

    @Transient
    private Account account;
    @NotNull
    @Min(100000)
    private BigDecimal amount;
    @Transient
    private Integer sourceAccount;

    public int getBeneficiary_account_id() {
        return beneficiary_account_id;
    }

    public void setBeneficiary_account_id(int beneficiary_account_id) {
        this.beneficiary_account_id = beneficiary_account_id;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "SavingsInfor{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", rollover='" + rollover + '\'' +
                ", interestRate=" + interestRate +
                ", term=" + term +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", sourceAccount=" + sourceAccount +
                '}';
    }


    public Integer getRollover() {
        return rollover;
    }

    public void setRollover(Integer rollover) {
        this.rollover = rollover;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Integer sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Integer getType() {
        return type;
    }

    public void setType(@NotNull Integer type) {
        this.type = type;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public double getProcess(){
        try{
            LocalDateTime localDateTime=LocalDateTime.ofInstant(Instant.ofEpochMilli(account.getOpenDate().getTime()), ZoneId.of("GMT+7"));
            long currentDays=ChronoUnit.DAYS.between(localDateTime,LocalDateTime.now(ZoneId.of("GMT+7")));
            long  maturityDays= ChronoUnit.DAYS.between(localDateTime,localDateTime.plusMonths(term));
            return currentDays*100.0/maturityDays;
        }catch (Exception e){
            return 0;
        }

    }
    public boolean isMature(){
        Calendar now=Calendar.getInstance();
        Calendar maturityDate=Calendar.getInstance();
        maturityDate.add(Calendar.MONTH,term);
        return now.after(maturityDate);
    }

}
