package com.cloud;

import com.cloud.configuration.Application;
import com.cloud.jpa.entity.Customer;
import com.cloud.jpa.entity.Goods;
import com.cloud.jpa.repository.CustomerRepository;
import com.cloud.jpa.repository.GoodsRepository;
import com.cloud.service.customer.impl.CustomerServiceImpl;
import com.cloud.service.customer.impl.TestServiceImpl;
import com.cloud.service.goods.GoodsService;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by sunhaidi on 2019-04-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class TransactionTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
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

    @Test
    public void jdbcTest(){
        Map<String,Object> params = new HashMap();
        params.put("pageSize",10);
        params.put("pageNo",1);
        params.put("name",ImmutableList.of("苗有"));//ImmutableList.of("妙有","苗有")
        params.put("likeName","%苗有%");

        StringBuffer sql = getSql(params,2);
        int pageSize = (Integer) params.get("pageSize");
        int pageNo = (Integer) params.get("pageNo");
        params.put("pageSize",pageSize);
        params.put("pageNo",(pageNo - 1) * pageSize);
        sql.append(" limit :pageNo, :pageSize");
        System.out.println(namedParameterJdbcTemplate.queryForObject(String.valueOf(sql),params, Integer.class));
//        List<Customer> list = namedParameterJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<Customer>(Customer.class));
//        System.out.println(JSON.toJSON(list));
        params.put("name",ImmutableList.of("苗有"));//ImmutableList.of("妙有","苗有")
        String s = "select * from customer where 1 = 1 and name in(:name)";
        String ss = "select * from customer where 1 = 1 and  name like :likeName";
//        System.out.println(jdbcTemplate.queryForList(s, new Object[]{"1"}));
        System.out.println(namedParameterJdbcTemplate.queryForList(s, params));
    }

    private StringBuffer getSql(Map param, int type) {
        StringBuffer sql = new StringBuffer();

        if ( type == 1 ) {
            sql.append("select * from customer where 1 = 1 ");
        }
        else {
            sql.append("select count(*) from customer where 1 = 1 ");
        }

        if ( param.get("name") != null && StringUtils.isNotBlank(param.get("name").toString())) {
            param.put("name","%"+param.get("name")+"%");
            sql.append(" AND name LIKE :name ");
        }
        sql.append(" order by update_time desc ");

        return sql;
    }

}
