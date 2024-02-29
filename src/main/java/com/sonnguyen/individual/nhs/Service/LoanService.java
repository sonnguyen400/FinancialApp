package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.ILoanRepository;
import com.sonnguyen.individual.nhs.Service.IService.ILoanService;

import javax.inject.Inject;

public class LoanService implements ILoanService {
    @Inject
    private ILoanRepository loanRepository;
}
