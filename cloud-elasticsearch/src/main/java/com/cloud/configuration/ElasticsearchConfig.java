package com.cloud.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cloud.repository")
public class ElasticsearchConfig {

    @Autowired
    private Environment env;

//    @Bean
//    public ElasticsearchTemplate getElasticsearchTemplate(Client client){
//
//        return  new ElasticsearchTemplate(client);
//    }

}
