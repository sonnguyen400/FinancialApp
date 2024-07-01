package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.Idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.service.iService.IPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private IPaymentDAO paymentRepository;
}
