package com.sonnguyen.individual.nhs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "savings_setting")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "term")
    private Integer term;
    @Column(name = "interest_rate")
    private Float interestRate;

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SavingsSetting{" +
                "id=" + id +
                ", term=" + term +
                ", interestRate=" + interestRate +
                '}';
    }
}
