package com.cloud.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cloud.service.CustomerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CustomerFeignService feignClientInterface;

    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    @RequestMapping(path = "nacos", method = RequestMethod.GET)
    public Object index() {
        System.out.println(username);
        return username;
    }

    @RequestMapping(value = "/customer/query/{uid}", method = RequestMethod.GET)
    public String query2(@PathVariable String uid) {
//        restTemplate.getForObject("http://customer-provider/customer/query/" + uid, String.class);
        return feignClientInterface.consumer(uid);
    }

}
