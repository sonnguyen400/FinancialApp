package com.sonnguyen.individual.nhs.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

@Table(name = "account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "branch_id")
    private int branchID;

    @Size(max = 45)
    @Column(name = "account_number", length = 45)
    private String accountNumber;

    @Size(max = 45)
    @Column(name = "account_type", length = 45)
    private String accountType;

    @Column(name = "balance", precision = 2)
    private BigDecimal balance;

    @Column(name = "open_date")
    private Instant openDate;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "overdraft_limit", precision = 2)
    private BigDecimal overdraftLimit;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "PIN")
    private String Pin;

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    @Transient
    private Collection<Customer> customers;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", branchID=" + branchID +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", openDate=" + openDate +
                ", status='" + status + '\'' +
                ", overdraftLimit=" + overdraftLimit +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customers=" + customers +
                '}';
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Instant getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Instant openDate) {
        this.openDate = openDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}