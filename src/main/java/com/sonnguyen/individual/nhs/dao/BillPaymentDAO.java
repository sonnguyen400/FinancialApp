package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.BillPayment;
import com.sonnguyen.individual.nhs.dao.Idao.IBillPaymentDAO;

import javax.enterprise.inject.Model;

@Model
public class BillPaymentDAO extends DAO<BillPayment,Integer> implements IBillPaymentDAO {
    @Override
    public Class<BillPayment> getEntityClass() {
        return BillPayment.class;
    }
}
