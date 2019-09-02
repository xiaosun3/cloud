package com.cloud.configuration;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanFactory {

    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
