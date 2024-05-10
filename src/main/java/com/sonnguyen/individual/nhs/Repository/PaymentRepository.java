package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.Payment;
import com.sonnguyen.individual.nhs.Repository.IRepository.IPaymentRepository;

import javax.enterprise.inject.Model;
import java.sql.SQLException;

@Model
public class PaymentRepository extends Repository<Payment,Integer> implements IPaymentRepository {
    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }


}
