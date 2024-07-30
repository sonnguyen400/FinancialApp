package com.sonnguyen.individual.nhs.service;

import com.sonnguyen.individual.nhs.dao.idao.IAccountDAO;
import com.sonnguyen.individual.nhs.dao.idao.IPaymentDAO;
import com.sonnguyen.individual.nhs.dao.impl.PaymentDAOImpl;
import com.sonnguyen.individual.nhs.model.Account;
import com.sonnguyen.individual.nhs.model.Payment;
import com.sonnguyen.individual.nhs.model.Transaction;
import com.sonnguyen.individual.nhs.model.Transfer;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.service.iservice.IPaymentService;
import com.sonnguyen.individual.nhs.service.iservice.ITransferService;
import javassist.NotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class PaymentService implements IPaymentService {
    @Inject
    private IPaymentDAO paymentDAO;
    @Inject
    private IAccountDAO accountDAO;
    @Inject
    private ITransferService transferService;

}
