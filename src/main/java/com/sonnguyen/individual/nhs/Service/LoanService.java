package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.ILoanRepository;
import com.sonnguyen.individual.nhs.Service.IService.ILoanService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
@Model
public class LoanService implements ILoanService {
    @Inject
    private ILoanRepository loanRepository;
}
