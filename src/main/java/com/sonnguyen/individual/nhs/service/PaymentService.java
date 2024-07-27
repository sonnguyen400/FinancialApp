package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.PaymentDAOImpl;
import com.sonnguyen.individual.nhs.service.iservice.IPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private PaymentDAOImpl paymentDAO;
}
