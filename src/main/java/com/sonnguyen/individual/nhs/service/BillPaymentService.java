package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.service.iservice.IBillPaymentService;
import com.sonnguyen.individual.nhs.dao.idao.IBillPaymentDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
@Model
public class BillPaymentService implements IBillPaymentService {
    @Inject
    private IBillPaymentDAO billPaymentRepository;
}
