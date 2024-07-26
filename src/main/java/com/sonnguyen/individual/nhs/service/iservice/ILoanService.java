package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.Constant.LoanStatus;
import com.sonnguyen.individual.nhs.model.Loan;
import javassist.NotFoundException;

import java.sql.SQLException;
import java.util.Collection;

public interface ILoanService{
    Loan updateStatusById(Integer id, LoanStatus status) throws SQLException, NotFoundException;
    Loan approveLoan(Integer id) throws SQLException, NotFoundException;
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Loan save(Loan loan);
    Collection<Loan> findAllByStatus(LoanStatus loanStatus);
    Collection<Loan> findAll();
}
