package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao.idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.dao.core.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Payment;

import javax.enterprise.inject.Model;

@Model
public class PaymentDAOImpl extends AbstractDAO<Payment,Integer> implements IPaymentDAO {
    @Override
    protected Class<Payment> getEntityType() {
        return Payment.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
