package com.sonnguyen.individual.nhs.Model;

import jakarta.persistence.*;

@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int term_id;
    private String terms_description;
    private String terms_due_days;
    public Term(int term_id, String terms_description, String terms_due_days) {
        this.term_id = term_id;
        this.terms_description = terms_description;
        this.terms_due_days = terms_due_days;
    }
}
