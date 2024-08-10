package com.sonnguyen.individual.nhs.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "account_holder")
public class AccountHolder {
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "customer_id")
    private int customerID;
    @Column(name = "is_default")
    private boolean isDefault;

    public AccountHolder(int accountID, int customerID) {
        this.accountID = accountID;
        this.customerID = customerID;
    }
    public AccountHolder(){}

    public AccountHolder(int customerID) {
        this.customerID = customerID;
    }

    @Transient
    public Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
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