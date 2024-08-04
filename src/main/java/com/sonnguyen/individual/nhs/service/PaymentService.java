package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.TransactionType;
import com.sonnguyen.individual.nhs.dao.core.DBTransaction;
import com.sonnguyen.individual.nhs.dao.idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.idao.ILoanDAO;
import com.sonnguyen.individual.nhs.dao.idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.model.*;
import com.sonnguyen.individual.nhs.service.iservice.IPaymentService;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import javassist.NotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private IPaymentDAO paymentDAO;
    @Inject
    private ILoanDAO loanDAO;
    @Inject
    private IAccountDAO accountDAO;
    @Inject
    private ITransferService transferService;
    @Inject
    private DBTransaction dbTransaction;
    @Override
    public Payment createPayment(int loanId, int srcAccount) throws NotFoundException {
        Calendar now = Calendar.getInstance();
        Loan loan=loanDAO.findById(loanId).orElseThrow(()->new IllegalArgumentException("Loan ID is invalid"));
        Account account=accountDAO.findById(srcAccount).orElseThrow(()->new NotFoundException("Source account not found"));
        Account branchAccount=accountDAO.findBranchPrincipalAccount(account.getBranchID()).orElseThrow(()->new NotFoundException("Branch account illegal"));
        //initial
        BigDecimal amount=calculateMonthlyPayment(loan);
        String message="Payment"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR)+" for loan "+loan.getId();
        //initialize payment
        Transaction transaction=new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(message);
        transaction.setAccountId(account.getId());
        transaction.setTransactionType(TransactionType.PAYOFF.value);
        //Initial transfer
        Transfer transfer=new Transfer();
        transfer.setMessage(message);
        transfer.setAccountId(branchAccount.getId());
        transfer.setTransaction(transaction);

        String transactionRef= transferService.init(transfer);
        return dbTransaction.startTransaction(Payment.class,connection -> {
            Transfer transfer1=transferService.transferCommit(connection,transactionRef);
            Payment payment=new Payment();
            payment.setAmount(amount);
            payment.setLoanId(loanId);
            payment.setTransactionId(transfer1.getTransaction().getId());
            payment.setId(paymentDAO.executeInsert(connection,payment));
            return payment;
        });
    }
    public List<Payment> findAllByLoanId(int loanID){
        return paymentDAO.findAllByLoanId(loanID);
    }

    @Override
    public int unpaidMonth(int loanID) {
        return paymentDAO.unpaidMonth(loanID);
    }
    @Override
    public BigDecimal calculateMonthlyPayment(Loan loan){
        int unpaidMonth=unpaidMonth(loan.getId());
        BigDecimal principal=loan.principal().multiply(BigDecimal.valueOf(unpaidMonth));
        BigDecimal interest=loan.interest();
        return principal.add(interest);
    }
    @Override
    public Date findNextPaymentByLoanId(int loanId){
        return paymentDAO.findNextPaymentByLoanId(loanId);
    }
}
