package com.cloud.jpa.repository;

import com.cloud.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sunhaidi on 2019-03-0``5.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByCard(String card);
}
