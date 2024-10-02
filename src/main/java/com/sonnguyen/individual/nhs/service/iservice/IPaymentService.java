package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Payment;
import javassist.NotFoundException;

import java.math.BigDecimal;
import java.sql.Date;

public interface IPaymentService {
    Payment createPayment(int loanId, int srcAccount) throws NotFoundException;

    int unpaidMonth(int loanID);
    /**
     * @Var Interest= amount*rate(%)/term
     * @Var Principal=amount/term
     * @Fomula payment= Principal+Interest + (Principal+Interest)*unpaidMonth + (Principal+Interest)*unpaidMonth*latePaymentCharge
     * @param loan (mandatory: term, amount,id ,interest rate)
     * @return payment
     */
    BigDecimal calculateMonthlyPayment(Loan loan);

    Date findNextPaymentByLoanId(int loanId);
}
