package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "account_holder")
public class AccountHolder {
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "customer_id")
    private int customerID;
}