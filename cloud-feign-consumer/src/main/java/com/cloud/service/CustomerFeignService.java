package com.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 定义feign client, value绑定消费的服务名称
 */
@FeignClient( value = "customer-provider" )
public interface CustomerFeignService {

    @GetMapping("/cloud-feign-provider/customer/query/{uid}")
    String consumer(@RequestParam("uid") String uid);

}

