package com.cloud.service.customer.impl;

import com.cloud.jpa.entity.Customer;
import com.cloud.jpa.entity.Goods;
import com.cloud.jpa.repository.CustomerRepository;
import com.cloud.service.customer.CustomerService;
import com.cloud.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sunhaidi on 2019-04-29.
 */
@Service
public class TestServiceImpl{
    @Autowired
    GoodsService goodsService;
    @Autowired
    CustomerService customerService;

    @Transactional()
    public void saveCustomer(Customer customer, Goods goods) {
        try {
            goodsService.save(goods);
            customerService.saveCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常");
        }
    }
}
