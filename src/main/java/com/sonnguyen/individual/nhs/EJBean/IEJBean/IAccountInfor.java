package com.sonnguyen.individual.nhs.EJBean.IEJBean;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;

import javax.ejb.Remote;

@Remote
public interface IAccountInfor {
    public Account getAccount();
    public Customer customer();
    public void update(Customer customer);

}
