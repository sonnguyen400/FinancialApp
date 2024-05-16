package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Constant.LoanStatus;
import com.sonnguyen.individual.nhs.Model.Loan;
import javassist.NotFoundException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ILoanService{
    Loan updateStatusById(Integer id, LoanStatus status) throws SQLException, NotFoundException;
    Loan approveLoan(Integer id) throws SQLException, NotFoundException;
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Loan save(Loan loan);
    Collection<Loan> findAllByStatus(LoanStatus loanStatus);
    Collection<Loan> findAll();
}
