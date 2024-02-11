package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "transaction_log")
public class TransactionLog {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "transaction_id")
    private int transactionId;

    @Size(max = 255)
    @Column(name = "detail")
    private String detail;

    @Column(name = "log_date")
    private Instant logDate;



}