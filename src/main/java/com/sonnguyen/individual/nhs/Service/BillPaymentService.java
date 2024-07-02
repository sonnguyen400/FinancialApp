package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Service.IService.IBillPaymentService;
import com.sonnguyen.individual.nhs.dao.Idao.IBillPaymentDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
@Model
public class BillPaymentService implements IBillPaymentService {
    @Inject
    private IBillPaymentDAO billPaymentRepository;
}
