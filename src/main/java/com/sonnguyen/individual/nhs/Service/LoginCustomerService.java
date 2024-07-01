package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Constant.DefaultBrand;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.dao.Idao.ILoginCustomerDAO;
import com.sonnguyen.individual.nhs.Service.IService.ILoginCustomerService;
import com.sonnguyen.individual.nhs.Constant.MemberShip;

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
