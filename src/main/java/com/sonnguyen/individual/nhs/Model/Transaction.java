package com.sonnguyen.individual.nhs.Model;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Size(max = 20)
    @Column(name = "transaction_type", length = 20)
    private String transactionType;

    @Column(name = "value", precision = 2)
    private BigDecimal value;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 45)
    @Column(name = "reference_number", length = 45)
    private String referenceNumber;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

}