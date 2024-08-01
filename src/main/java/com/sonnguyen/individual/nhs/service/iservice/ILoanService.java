package com.sonnguyen.individual.nhs.service.iservice;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sonnguyen.individual.nhs.model.Loan;
import javassist.NotFoundException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface ILoanService{
    Optional<Loan> findById(int id);
    Loan updateStatusById(Integer id, LoanStatus status) throws SQLException, NotFoundException;
    Loan approveLoan(Integer id) throws SQLException, NotFoundException;
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Loan save(Loan loan);
    Collection<Loan> findAllByStatus(LoanStatus loanStatus);
    Collection<Loan> findAll();

}
