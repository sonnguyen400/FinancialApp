package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Loan;

import java.math.BigDecimal;

public interface IPaymentService {
    int unpaidMonth(int loanID);

    BigDecimal calculateMonthlyPayment(Loan loan);
}
