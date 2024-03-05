package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Loan;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoanRepository;

import javax.enterprise.inject.Model;

@Model
public class LoanRepository extends Repository<Loan,Integer> implements ILoanRepository {
    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
