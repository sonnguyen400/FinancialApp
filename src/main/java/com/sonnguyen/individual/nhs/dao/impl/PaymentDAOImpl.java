package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.Payment;

import javax.enterprise.inject.Model;

@Model
public class PaymentDAOImpl extends AbstractDAO<Payment,Integer> {
    @Override
    protected Class<Payment> getEntityType() {
        return Payment.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
