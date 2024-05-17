package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "account_holder")
public class AccountHolder {
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "customer_id")
    private int customerID;
    @Column(name="account_type")
    private String accountType;

    public AccountHolder(int accountID, int customerID, String accountType) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.accountType = accountType;
    }

    public AccountHolder() {
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}