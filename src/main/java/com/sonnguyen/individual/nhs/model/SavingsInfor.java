package com.sonnguyen.individual.nhs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "savings_infor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsInfor {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "rollover")
    private String rollover;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @Column(name = "term")
    private Integer term;
    @Column(name = "account_id")
    private Integer accountId;
    @Transient
    private Account account;
    @Transient
    private BigDecimal amount;
    @Transient
    private Integer sourceAccount;

    @Override
    public String toString() {
        return "SavingsInfor{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", rollover='" + rollover + '\'' +
                ", interestRate=" + interestRate +
                ", term=" + term +
                ", accountId=" + accountId +
                ", account=" + account +
                ", amount=" + amount +
                ", sourceAccount=" + sourceAccount +
                '}';
    }

    public String getRollover() {
        return rollover;
    }

    public void setRollover(String rollover) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
