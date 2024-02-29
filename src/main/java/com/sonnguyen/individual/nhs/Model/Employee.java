package com.sonnguyen.individual.nhs.Model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "role_id")
    private Integer roleId;

    @Size(max = 45)
    @Column(name = "firstname", length = 45)
    private String firstname;

    @Size(max = 45)
    @Column(name = "lastname", length = 45)
    private String lastname;

    @Size(max = 45)
    @Column(name = "position", length = 45)
    private String position;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 12)
    @Column(name = "phone", length = 12)
    private String phone;

    @Column(name = "salary", precision = 2)
    private BigDecimal salary;


}