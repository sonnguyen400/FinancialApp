package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.IPaymentRepository;
import com.sonnguyen.individual.nhs.Service.IService.IPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private IPaymentRepository paymentRepository;
}
