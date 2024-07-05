package com.sonnguyen.individual.nhs.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "saving_limit")
    private BigDecimal saving_limit;
    @Column(name = "loan_limit")
    private BigDecimal loan_limit;
}
