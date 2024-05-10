package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Repository.IRepository.ILoginCustomerRepository;
import com.sonnguyen.individual.nhs.Service.IService.ILoginCustomerService;
import com.sonnguyen.individual.nhs.Utils.MemberShip;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;

@Model
public class LoginCustomerService implements ILoginCustomerService {
    @Inject
    ILoginCustomerRepository loginCustomerRepository;
    @Override
    public Customer save(Login login, Customer customer) {
        Account account=new Account();
        account.setBranchID(1);
        account.setBalance(BigDecimal.valueOf(1000000));
        customer.setMembership(MemberShip.STANDARD.value);
        return loginCustomerRepository.save(login,customer,account);
    }
}
