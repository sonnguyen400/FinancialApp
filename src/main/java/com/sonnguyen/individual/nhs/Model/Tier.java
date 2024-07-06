package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tier")
public class Tier {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "overdraft_limit", precision = 9, scale = 2)
    private BigDecimal overdraftLimit;

    @Size(max = 45)
    @Column(name = "limit_transaction", length = 45)
    private BigDecimal limitTransaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public @Size(max = 45) BigDecimal getLimitTransaction() {
        return limitTransaction;
    }

    public void setLimitTransaction(@Size(max = 45) BigDecimal limitTransaction) {
        this.limitTransaction = limitTransaction;
    }
}