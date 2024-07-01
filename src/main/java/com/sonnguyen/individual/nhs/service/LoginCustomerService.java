package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.DefaultBrand;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginCustomerDAO;
import com.sonnguyen.individual.nhs.service.iService.ILoginCustomerService;
import com.sonnguyen.individual.nhs.constant.MemberShip;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Model
public class LoginCustomerService implements ILoginCustomerService {
    @Inject
    ILoginCustomerDAO loginCustomerRepository;
    @Override
    public Customer save(Login login, Customer customer) {
        Account account=new Account();
        account.setBranchID(DefaultBrand.ID.getValue());
        account.setBalance(BigDecimal.valueOf(1000000));
        account.setAccountNumber(((List<Account>)customer.getAccounts()).get(0).getAccountNumber());
        customer.setMembership(MemberShip.STANDARD.value);
        return loginCustomerRepository.save(login,customer,account);
    }
}
