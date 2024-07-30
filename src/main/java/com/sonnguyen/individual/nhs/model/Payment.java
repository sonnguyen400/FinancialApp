package com.sonnguyen.individual.nhs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "amount", precision = 2)
    private double amount;

    @Column(name = "payment_date",insertable = false)
    private Date paymentDate;

    @Size(max = 45)
    @Column(name = "payment_status", length = 45)
    private String paymentStatus;

    @Column(name = "transaction_id")
    private int transactionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public @Size(max = 45) String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@Size(max = 45) String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}