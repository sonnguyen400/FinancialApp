package com.sonnguyen.individual.nhs.EJBean;

import com.sonnguyen.individual.nhs.EJBean.IEJBean.IAccountInfor;
import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Customer;

import javax.ejb.Stateless;

@Stateless
public class AccountInfo implements IAccountInfor {

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public Customer customer() {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }
}
