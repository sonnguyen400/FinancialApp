package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Payment;
import javassist.NotFoundException;

import java.math.BigDecimal;
import java.sql.Date;

public interface IPaymentService {
    Payment createPayment(int loanId, int srcAccount) throws NotFoundException;

    int unpaidMonth(int loanID);

    BigDecimal calculateMonthlyPayment(Loan loan);

    Date findNextPaymentByLoanId(int loanId);
}
