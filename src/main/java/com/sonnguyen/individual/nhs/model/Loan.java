package com.sonnguyen.individual.nhs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "loan")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "customer_id")
    private int customerId;

    @Size(max = 20)
    @Column(name = "type", length = 20)
    private String type;

    @Column(name = "amount", precision = 2)
    private BigDecimal amount;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "term")
    private Integer term;

    @Column(name = "approval_date")
    private Date approvalDate;
    @Column(name = "create_at")
    private Date createAt;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 45)
    @Column(name = "repayment", length = 45)
    private String repayment;
    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "disbursement_account_number")
    private String disbursementAccountNumber;
    @Transient
    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDisbursementAccountNumber() {
        return disbursementAccountNumber;
    }

    public void setDisbursementAccountNumber(String disbursementAccountNumber) {
        this.disbursementAccountNumber = disbursementAccountNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }



    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", term=" + term +
                ", approvalDate=" + approvalDate +
                ", status='" + status + '\'' +
                ", repayment='" + repayment + '\'' +
                ", branchId=" + branchId +
                ", disbursementAccountNumber='" + disbursementAccountNumber + '\'' +
                '}';
    }
}