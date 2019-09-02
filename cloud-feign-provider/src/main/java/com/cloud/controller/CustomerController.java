package com.cloud.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
public class CustomerController {
    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    @RequestMapping(value = "/customer/query/{uid}", method = RequestMethod.GET)
    public Object query(@PathVariable String uid) {

        return String.format("username:%s uid:%s", username, uid);
    }

    @RequestMapping(path = "customer/query", method = RequestMethod.GET)
    public String index(@RequestParam String userId){
        return userId;
    }
}
