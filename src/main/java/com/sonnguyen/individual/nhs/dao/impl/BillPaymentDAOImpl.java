package com.sonnguyen.individual.nhs.dao.impl;

import com.sonnguyen.individual.nhs.dao_v2.AbstractDAO;
import com.sonnguyen.individual.nhs.model.BillPayment;

import javax.enterprise.inject.Model;

@Model
public class BillPaymentDAOImpl extends AbstractDAO<BillPayment,Integer> {
    @Override
    protected Class<BillPayment> getEntityType() {
        return BillPayment.class;
    }

    @Override
    protected Class<Integer> getIdType() {
        return Integer.class;
    }
}
