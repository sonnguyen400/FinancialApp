package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Loan;

public class LoanRepository extends Repository<Loan,Integer> {
    private static final LoanRepository loanRepository = new LoanRepository();
    public static LoanRepository getInstance() {
        return loanRepository;
    }
}
