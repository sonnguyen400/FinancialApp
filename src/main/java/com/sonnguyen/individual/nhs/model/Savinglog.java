package com.sonnguyen.individual.nhs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "savings_log")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Savinglog {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "detail")
    private String details;
    @Column(name = "created_at")
    private Date date;
    @Column(name="savings_infor_id")
    private Integer savings_infor_id;
    @Column(name = "savings_infor_account_id")
    private Integer savings_infor_account_id;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSavings_infor_id() {
        return savings_infor_id;
    }

    public void setSavings_infor_id(Integer savings_infor_id) {
        this.savings_infor_id = savings_infor_id;
    }

    public Integer getSavings_infor_account_id() {
        return savings_infor_account_id;
    }

    public void setSavings_infor_account_id(Integer savings_infor_account_id) {
        this.savings_infor_account_id = savings_infor_account_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
