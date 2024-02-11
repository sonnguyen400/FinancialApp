package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private int address_id;
    private String email;
    private String phone;
    private String social_security_number;
    private String occupation;
}
