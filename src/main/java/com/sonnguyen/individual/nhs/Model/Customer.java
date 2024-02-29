package com.sonnguyen.individual.nhs.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
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
