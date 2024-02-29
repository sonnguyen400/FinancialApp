package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.IBillPaymentRepository;
import com.sonnguyen.individual.nhs.Service.IService.IBillPaymentService;

import javax.inject.Inject;

public class BillPaymentService implements IBillPaymentService {
    @Inject
    private IBillPaymentRepository billPaymentRepository;
}
