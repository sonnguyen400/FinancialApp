package com.sonnguyen.individual.nhs.Repository.IRepository;

import com.sonnguyen.individual.nhs.Model.Customer;

public class CustomerRepository extends Repository<Customer,Integer>{
    private static final CustomerRepository customerRepository=new CustomerRepository();
    public static CustomerRepository getInstance(){
        return customerRepository;
    }

}
