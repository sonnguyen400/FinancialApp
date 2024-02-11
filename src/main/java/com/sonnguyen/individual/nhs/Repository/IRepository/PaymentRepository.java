package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Payment;

public class PaymentRepository extends Repository<Payment,Integer> {
    private static final PaymentRepository paymentRepository = new PaymentRepository();
    public static PaymentRepository getInstance() {
        return paymentRepository;
    }
}
