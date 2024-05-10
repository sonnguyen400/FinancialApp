package com.sonnguyen.individual.nhs.Utils;

import com.sonnguyen.individual.nhs.Model.Loan;

public enum LoanStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");
    public String value;
    LoanStatus(String value) {
        this.value = value;
    }
}
