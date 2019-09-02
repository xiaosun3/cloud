package com.cloud.configuration;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.cloud.service.CustomerFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients(basePackages = "com.cloud")//开启Spring Cloud Feign的支持功能。
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.cloud")
@NacosPropertySource(dataId = "customer_config", autoRefreshed = true, groupId = "DEFAULT_GROUP")
public class Application extends SpringBootServletInitializer {

    @Autowired
    AbstractApplicationContext c;
    @Autowired
    CustomerFeignService feignClientInterface;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);


    }

    @RestController
    public class TestController {
        @Autowired
        RestTemplate restTemplate;

        @Autowired
        public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

        @RequestMapping(value = "/index", method = RequestMethod.GET)
        public String index(@RequestParam String uid) {
            return feignClientInterface.consumer(uid);
        }
    }

}
