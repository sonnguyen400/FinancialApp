package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.dao.Idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.Service.IService.IPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private IPaymentDAO paymentRepository;
}
