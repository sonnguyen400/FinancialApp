package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.AccountTier;
import com.sonnguyen.individual.nhs.Constant.AccountType;
import com.sonnguyen.individual.nhs.Constant.DefaultBrand;
import com.sonnguyen.individual.nhs.Constant.MemberShip;
import com.sonnguyen.individual.nhs.Exception.RegisterException;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ILoginCustomerService;
import com.sonnguyen.individual.nhs.Service.IService.ILoginService;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginCustomerDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

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
