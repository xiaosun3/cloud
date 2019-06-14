package com.cloud.service.customer.impl;

import com.cloud.jpa.entity.Customer;
import com.cloud.jpa.repository.CustomerRepository;
import com.cloud.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sunhaidi on 2019-04-29.
 */
@Service
public class CustomerServiceImpl  implements  CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    @Override
    @Transactional()
    public void saveCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常2");
        }
    }
}
