package com.cloud;

import com.cloud.configuration.Application;
import com.cloud.jpa.entity.Customer;
import com.cloud.jpa.entity.Goods;
import com.cloud.jpa.repository.CustomerRepository;
import com.cloud.jpa.repository.GoodsRepository;
import com.cloud.service.customer.impl.CustomerServiceImpl;
import com.cloud.service.customer.impl.TestServiceImpl;
import com.cloud.service.goods.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


/**
 * Created by sunhaidi on 2019-04-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class TransactionTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    TestServiceImpl TestServiceImpl;

    @Test
    @Transactional
    public void test(){
        try {
            Customer customer = new Customer("孙海迪", "7");
            customer.setNickname("7");
            customerService.saveCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常了1");
        }
    }

    @Test
    public void test2(){
        try {
            TestServiceImpl.saveCustomer(new Customer("孙海迪", "6"), new Goods("name","desc"));
//            goodsService.save(new Goods("name","desc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
