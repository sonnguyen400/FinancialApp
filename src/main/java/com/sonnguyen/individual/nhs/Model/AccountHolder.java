package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "account_holder")
public class AccountHolder {
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "customer_id")
    private int customerID;
    @Column(name = "is_default")
    private boolean isDefault;
    @Column(name = "is_default")
    private boolean isDefault;

    public AccountHolder(int accountID, int customerID) {
        this.accountID = accountID;
        this.customerID = customerID;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public AccountHolder() {
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