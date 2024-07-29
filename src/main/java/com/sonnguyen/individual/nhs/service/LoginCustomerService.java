package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.AccountTier;
import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.constant.MemberShip;
import com.sonnguyen.individual.nhs.constant.AccountType;
import com.sonnguyen.individual.nhs.dao.idao.ILoginCustomerDAO;
import com.sonnguyen.individual.nhs.exception.RegisterException;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.ICustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginCustomerService;
import com.sonnguyen.individual.nhs.service.iservice.ILoginService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;

@Model
public class LoginCustomerService implements ILoginCustomerService {
    @Inject
    ILoginCustomerDAO loginCustomerRepository;
    @Inject
    ICustomerService customerService;
    @Inject
    IAccountService accountService;
    @Inject
    ILoginService loginService;
    @Override
    public Customer save(Login login, Customer customer,Account account ) {
        account.setBranchID(DefaultBrand.ID.value);
        account.setBalance(BigDecimal.valueOf(1000000));
        if(account.getAccountNumber()==null) account.setAccountNumber(customer.getPhone());
        account.setTierID(AccountTier.SILVER.id);
        account.setAccountType(AccountType.PRINCIPAL.value);

        customer.setMembershipID(MemberShip.STANDARD.value);
        if(!customerService.isValid(customer.getEmail(),customer.getPhone(),customer.getSocial_security_number())||
                accountService.findByAccountNumber(account.getAccountNumber()).isPresent()||
                    loginService.findByUsername(login.getUsername()).isPresent()
        ){
            throw new RegisterException("Check out your information again!");
        }
        return loginCustomerRepository.save(login,customer,account);
    }
}
