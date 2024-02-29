package com.sonnguyen.individual.nhs.Service;

import com.sonnguyen.individual.nhs.Repository.IRepository.ICustomerRepository;
import com.sonnguyen.individual.nhs.Service.IService.ICustomerService;

import javax.inject.Inject;

public class CustomerService implements ICustomerService {
    @Inject
    private ICustomerRepository customerRepository;
}
