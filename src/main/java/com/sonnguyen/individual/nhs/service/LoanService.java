package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.LoanStatus;
import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.dao.idao.ILoanDAO;
import com.sonnguyen.individual.nhs.dao.impl.LoanDAOImpl;
import com.sonnguyen.individual.nhs.dao.core.DBTransaction;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import javassist.NotFoundException;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Model
public class LoanService implements ILoanService {
    @Inject
    private ILoanDAO loanDAO;
    @Inject
    private ITransferService transferService;
    @Inject
    private IAccountService accountService;
    @Inject
    private DBTransaction dbTransaction;
    Logger logger=Logger.getLogger(this.getClass());
    @Override
    public Collection<Loan> findAllByCustomerId(Integer customerId) {
        return loanDAO.findAllByCustomerId(customerId);
    }

    @Override
    public Loan save(Loan loan) {
        loan.setStatus(LoanStatus.PENDING.value);
        loan.setId(loanDAO.executeInsert(loan));
        return  loan ;
    }

    @Override
    public Collection<Loan> findAllByStatus(LoanStatus loanStatus) {
        return loanDAO.findAllByStatus(loanStatus.value);
    }
    public Collection<Loan> findAll(){
        return loanDAO.findAll();
    }

    @Override
    public Optional<Loan> findById(int id) {
        return loanDAO.findById(id);
    }

    @Override
    public Loan updateStatusById(Integer id, LoanStatus status) throws SQLException, NotFoundException {
        loanDAO.updateStatusById(id, status.value);
        return loanDAO.findById(id).orElseThrow(()->new NotFoundException("Could not find"));
    }

    @Override
    public Loan approveLoan(Integer id) throws SQLException, NotFoundException {
        Loan loan=loanDAO.findById(id).orElseThrow(()->new NotFoundException("Could not find"));
        Account account=accountService.findByAccountNumber(loan.getDisbursementAccountNumber()).orElseThrow(()->new NotFoundException("Could not find"));
        return dbTransaction.startTransaction(Loan.class,connection -> {
            Transaction transaction=new Transaction();
            transaction.setAmount(loan.getAmount());
            transaction.setAccountId(1);
            transaction.setDescription("Disburse for loan");
            transaction.setTransactionType(TransactionType.DISBURSEMENT.value);
            Transfer transfer = new Transfer();
            transfer.setMessage("Disburse for loan");
            transfer.setAccountId(account.getId());
            transfer.setTransaction(transaction);
            loanDAO.updateStatusById(connection,id,LoanStatus.APPROVED.value);
            transferService.startTransfer(connection,transfer);
            loan.setStatus(LoanStatus.APPROVED.value);
            return loan;
        });
    }
}
