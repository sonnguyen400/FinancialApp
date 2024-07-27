package com.sonnguyen.individual.nhs.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
    private Integer accountType;

    @Column(name = "balance", precision = 2)
    private BigDecimal balance;

    @Column(name = "open_date",insertable = false)
    private Date openDate;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private Integer status;
    @Column(name = "tier_id")
    private int tierID;

    @Transient
    private Tier tier;

    @Transient
    private Collection<Customer> customers;
    @Transient
    private Collection<AccountHolder> accountHolders;

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
                ", overdraftLimit="  +
                ", customers=" + customers +
                '}';
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public int getTierID() {
        return tierID;
    }

    public void setTierID(int tierID) {
        this.tierID = tierID;
    }

    public Collection<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(Collection<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }



    public @Size(max = 45) Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(@Size(max = 45) Integer accountType) {
        this.accountType = accountType;
    }

    public @Size(max = 45) Integer getStatus() {
        return status;
    }

    public void setStatus(@Size(max = 45) Integer status) {
        this.status = status;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
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



    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }





}