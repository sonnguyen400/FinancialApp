package com.sonnguyen.individual.nhs.Repository;

import com.sonnguyen.individual.nhs.Model.BillPayment;
import com.sonnguyen.individual.nhs.Repository.IRepository.IBillPaymentRepository;

public class BillPaymentRepository extends Repository<BillPayment,Integer> implements IBillPaymentRepository {
    private static final BillPaymentRepository billPaymentRepository = new BillPaymentRepository();
    public static BillPaymentRepository getInstance() {
        return billPaymentRepository;
    }
}
