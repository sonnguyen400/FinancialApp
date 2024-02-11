package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "amount", precision = 2)
    private double amount;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Size(max = 45)
    @Column(name = "payment_status", length = 45)
    private String paymentStatus;

    @Column(name = "transaction_id")
    private int transactionId;


}