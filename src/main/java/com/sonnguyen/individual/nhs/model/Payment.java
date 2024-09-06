package com.sonnguyen.individual.nhs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoField;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "amount", precision = 2)
    private BigDecimal amount;

    @Column(name = "payment_date",insertable = false)
    private Instant paymentDate;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
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
    public boolean after(int month, int year) {
        int curMonth = paymentDate.get(ChronoField.MONTH_OF_YEAR);
        int curYear = paymentDate.get(ChronoField.YEAR);
        return( month>curMonth&&year==curYear)||year>curYear;
    }
    public boolean before(int month, int year) {
        int curMonth = paymentDate.get(ChronoField.MONTH_OF_YEAR);
        int curYear = paymentDate.get(ChronoField.YEAR);
        return (month<curMonth&&year==curYear)||year<curYear;
    }
    public boolean in(int month, int year) {
        int curMonth = paymentDate.get(ChronoField.MONTH_OF_YEAR);
        int curYear = paymentDate.get(ChronoField.YEAR);
        return month==curMonth&&year==curYear;
    }
}