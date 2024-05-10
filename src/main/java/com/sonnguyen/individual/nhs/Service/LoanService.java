package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Loan;
import com.sonnguyen.individual.nhs.Repository.GeneralRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoanRepository;
import com.sonnguyen.individual.nhs.Service.IService.ILoanService;
import com.sonnguyen.individual.nhs.Utils.LoanStatus;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Model
public class LoanService implements ILoanService {
    @Inject
    private ILoanRepository loanRepository;

    @Override
    public Collection<Loan> findAllByCustomerId(Integer customerId) {
        return loanRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Loan save(Loan loan) {
        loan.setStatus(LoanStatus.PENDING.value);
        return loanRepository.save(loan);
    }
}
