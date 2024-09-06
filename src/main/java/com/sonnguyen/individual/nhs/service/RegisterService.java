package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.constant.*;
import com.sonnguyen.individual.nhs.context.annotation.Value;
import com.sonnguyen.individual.nhs.dao.core.DBTransaction;
import com.sonnguyen.individual.nhs.dao.idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.idao.IAccountHolderDAO;
import com.sonnguyen.individual.nhs.dao.idao.ICustomerDAO;
import com.sonnguyen.individual.nhs.dao.idao.ILoginDAO;
import com.sonnguyen.individual.nhs.exception.RegisterException;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.AccountHolder;
import com.sonnguyen.individual.nhs.model.Customer;
import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.service.iservice.IRegisterService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.SQLException;

@Model
public class RegisterService implements IRegisterService {
    @Value(name ="register.account.initialAmount" )
    private Integer initialAmount;
    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private ILoginDAO loginDAO;
    @Inject
    private IAccountDAO accountDAO;
    @Inject
    private IAccountHolderDAO accountHolderDAO;
    @Inject
    private DBTransaction dbTransaction;
    @Override
    public Customer register(Login login, Customer customer,Account account ) throws SQLException {

        if(!customerDAO.isValid(customer.getEmail(),customer.getPhone(),customer.getSocial_security_number())||
                accountDAO.findByAccountNumber(account.getAccountNumber()).isPresent()||
                loginDAO.findByUsername(login.getUsername()).isPresent()
        ){
            throw new RegisterException("Duplicated register information? Check out your information again!");
        }
        account.setBranchID(DefaultBrand.ID.value);
        account.setBalance(BigDecimal.valueOf(initialAmount));
        if(account.getAccountNumber()==null||account.getAccountNumber().isEmpty()) account.setAccountNumber(customer.getPhone());
        account.setTierID(AccountTier.SILVER.id);
        account.setAccountType(AccountType.PRIMARY.value);
        customer.setMembershipID(MemberShip.STANDARD.value);
        account.setStatus(AccountStatus.OPEN.value);
        return dbTransaction.startTransaction(Customer.class,connection -> {
            Integer customerId=customerDAO.executeInsert(connection,customer);
            login.setCustomerId(customerId);
            Integer loginId=loginDAO.executeInsert(connection,login);
            Integer accountId=accountDAO.executeInsert(connection,account);
            AccountHolder accountHolder = new AccountHolder();
            accountHolder.setCustomerID(customerId);
            accountHolder.setAccountID(accountId);
            accountHolder.setDefault(true);
            accountHolderDAO.executeInsert(connection,accountHolder);
            return customer;
        });
    }
}
