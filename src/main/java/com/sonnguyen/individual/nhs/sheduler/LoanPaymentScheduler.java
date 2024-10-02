package com.sonnguyen.individual.nhs.sheduler;

import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Loan;
import com.sonnguyen.individual.nhs.service.SendGridEmailService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoanService;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
@Scheduled(cronExpression = "0 0 3 * * ?")
public class LoanPaymentScheduler implements Job {
    @Inject
    private ILoanService loanService;
    @Inject
    private SendGridEmailService sendGridEmailService;
    @Inject
    ICustomerService customerService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<Loan> loans=loanService.findAllByNextPaymentDate(3);
        for (Loan loan : loans) {
            Customer customer=customerService.findById(loan.getCustomerId()).get();
            sendGridEmailService.sendEmail(customer.getEmail(),"Your loan is close to the payment date","Loan alert");
        }
    }
}
