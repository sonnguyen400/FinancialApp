package com.sonnguyen.individual.nhs.dao;

import com.sonnguyen.individual.nhs.model.Payment;
import com.sonnguyen.individual.nhs.dao.Idao.IPaymentDAO;

import javax.enterprise.inject.Model;

@Model
public class PaymentDAO extends DAO<Payment,Integer> implements IPaymentDAO {
    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }


}
