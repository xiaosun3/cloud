package com.cloud.configuration;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.support.AbstractApplicationContext;

@EnableDiscoveryClient //开启服务注册发现的功能
@SpringBootApplication(scanBasePackages = "com.cloud")
@NacosPropertySource(dataId = "customer_config", autoRefreshed = true, groupId = "DEFAULT_GROUP")
public class Application extends SpringBootServletInitializer {

    @Autowired
    AbstractApplicationContext c;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        //elasticseach 和redis底层都用了 Netty
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);



    }
}
