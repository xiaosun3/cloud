package com.cloud.configuration;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.support.AbstractApplicationContext;


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
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);


    }


}
