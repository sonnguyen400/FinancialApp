package com.sonnguyen.individual.nhs;


import com.sonnguyen.individual.nhs.Constant.AccountStatus;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Repository.AccountRepository;

import java.util.List;

public class HelloServlet {
    public static void main(String[] args){
        AccountRepository accountRepository=new AccountRepository();
        List<Account> accs=accountRepository.findByStatusAndTypeAndCustomerId(AccountStatus.OPEN.value, AccountType.SAVINGS.value,9);
        System.out.println(accs.size());
    }
}