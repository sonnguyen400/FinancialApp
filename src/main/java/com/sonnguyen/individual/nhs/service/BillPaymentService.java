package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.Idao.IBillPaymentDAO;
import com.sonnguyen.individual.nhs.service.iService.IBillPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
@Model
public class BillPaymentService implements IBillPaymentService {
    @Inject
    private IBillPaymentDAO billPaymentRepository;
}
