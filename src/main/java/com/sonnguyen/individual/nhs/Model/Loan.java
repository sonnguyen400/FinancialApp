package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "loan")
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
    private Float interestRate;

    @Column(name = "term")
    private Integer term;

    @Column(name = "approval_date")
    private Instant approvalDate;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 45)
    @Column(name = "repayment", length = 45)
    private String repayment;

    @Column(name = "branch_id")
    private int branchId;


}