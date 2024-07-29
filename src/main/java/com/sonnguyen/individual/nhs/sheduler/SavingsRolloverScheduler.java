package com.sonnguyen.individual.nhs.sheduler;

import com.sonnguyen.individual.nhs.constant.Rollover;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.SavingsInfo;
import com.sonnguyen.individual.nhs.service.AccountService;
import com.sonnguyen.individual.nhs.service.SavingInfoService;
import com.sonnguyen.individual.nhs.service.SendGridEmailService;
import com.sun.istack.logging.Logger;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.inject.Model;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

@Scheduled(cronExpression = "0 0 2 * * ?")
@Model
public class SavingsRolloverScheduler implements Job {
    @Autowired
    private SavingInfoService savingInfoService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SendGridEmailService sendGridEmailService;

    private Logger logger=Logger.getLogger(SavingsRolloverScheduler.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<SavingsInfo> savingsInfos=savingInfoService.findAllMaturity();
        for (SavingsInfo savingsInfo : savingsInfos) {
            try {
                Account linkedAccount=accountService.findById(savingsInfo.getAccountId()).orElseThrow(()->new ResourceNotFoundException("Account Not Found"));
                Account branchAccount=accountService.findBranchPrincipalAccount(linkedAccount.getBranchID()).orElseThrow(()->new ResourceNotFoundException("Management Branch isn't found"));
                if(savingsInfo.getRollover()== Rollover.WITHDRAW_ENTIRE.value){
                    savingInfoService.withdrawEntire(savingsInfo,linkedAccount,branchAccount,savingsInfo.getBeneficiary_account_id());
                } else if (savingsInfo.getRollover()==Rollover.ROLLOVER_PRINCIPAL.value) {
                    savingInfoService.withdrawEntire(savingsInfo,linkedAccount,branchAccount,savingsInfo.getAccountId());
                } else if (savingsInfo.getRollover()==Rollover.ROLLOVER_ALL.value) {
                    savingInfoService.rollOverAll(savingsInfo,linkedAccount,branchAccount);
                }
            } catch (Exception e) {
                logger.log(Level.ALL,"Error while complete savings with id: "+savingsInfo.getId());
                logger.log(Level.ALL,"Details sent to admin email");
                try {
                    sendGridEmailService.sendEmail("hellohoangson@gmail.com",e.getMessage(),"Something went wrong").get();
                } catch (InterruptedException | ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}