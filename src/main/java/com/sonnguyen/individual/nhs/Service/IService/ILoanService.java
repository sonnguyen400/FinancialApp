package com.sonnguyen.individual.nhs.Service.IService;

import com.sonnguyen.individual.nhs.Model.Loan;

import java.util.Collection;
import java.util.List;

public interface ILoanService{
    Collection<Loan> findAllByCustomerId(Integer customerId);
    Loan save(Loan loan);
}
