package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.BillPayment;
import com.sonnguyen.individual.nhs.Repository.IRepository.IBillPaymentRepository;

import javax.enterprise.inject.Model;

@Model
public class BillPaymentRepository extends Repository<BillPayment,Integer> implements IBillPaymentRepository {
    @Override
    public Class<BillPayment> getEntityClass() {
        return BillPayment.class;
    }
}
