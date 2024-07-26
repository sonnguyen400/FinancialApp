package com.sonnguyen.individual.nhs.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "transaction_type")
    private int transactionType;

    @Column(name = "amount",scale = 9,precision = 2)
    private BigDecimal amount;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 45)
    @Column(name = "reference_number", length = 45)
    private String referenceNumber;

    @Column(name = "status")
    private Integer status;

    @Column(name = "transaction_at")
    private Date transactionAt;

    public Date getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(Date transactionAt) {
        this.transactionAt = transactionAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    private Transfer transfer;

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public Transfer getTransfer() {
        return transfer;
    }


    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

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



    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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




    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", transactionType='" + transactionType + '\'' +
                ", value=" + amount +
                ", description='" + description + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", status='"  + '\'' +
                '}';
    }
}