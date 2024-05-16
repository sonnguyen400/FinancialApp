package com.sonnguyen.individual.nhs.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Size(max = 20)
    @Column(name = "transaction_type", length = 20)
    private String transactionType;

    @Column(name = "value",scale = 9,precision = 2)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", transactionType='" + transactionType + '\'' +
                ", value=" + value +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}