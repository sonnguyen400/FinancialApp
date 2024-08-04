package com.sonnguyen.individual.nhs.dao.idao;

import com.sonnguyen.individual.nhs.dao.core.GeneralDAO;
import com.sonnguyen.individual.nhs.model.Payment;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IPaymentDAO extends GeneralDAO<Payment,Integer> {
    List<Payment> findAllByLoanId(int loanId);
    Optional<Payment> findAllByLoanId(int loanId, int month, int year);
    int unpaidMonth(int loanID);

    Date findNextPaymentByLoanId(int loanId);
}
