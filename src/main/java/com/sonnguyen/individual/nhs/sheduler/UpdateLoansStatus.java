package com.sonnguyen.individual.nhs.sheduler;

import com.sonnguyen.individual.nhs.service.LoanService;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Scheduled(cronExpression = "0 0 2 * * ?")
@Model
public class UpdateLoansStatus implements Job
{
    @Inject
    private LoanService service;
    public UpdateLoansStatus(LoanService service) {
        this.service = service;
    }
    public UpdateLoansStatus() {
    }
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
    }
}