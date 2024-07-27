package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.impl.BillPaymentDAOImpl;
import com.sonnguyen.individual.nhs.service.iservice.IBillPaymentService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
@Model
public class BillPaymentService implements IBillPaymentService {
    @Inject
    private BillPaymentDAOImpl billPaymentRepository;
}
