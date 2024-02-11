package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
public class Account {
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
    private Boolean balance;

    @Column(name = "open_date")
    private Instant openDate;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "overdraft_limit", precision = 2)
    private BigDecimal overdraftLimit;

}