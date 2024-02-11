package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.BillPayment;

public class BillPaymentRepository extends Repository<BillPayment,Integer> {
    private static final BillPaymentRepository billPaymentRepository = new BillPaymentRepository();
    public static BillPaymentRepository getInstance() {
        return billPaymentRepository;
    }
}
