//package com.cloud.configuration;
//
//import com.cloud.filters.RequestWrapperFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//
//@Configuration
//public class BeanFactory {
//
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public FilterRegistrationBean SignFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new RequestWrapperFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("RequestWrapperFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        return registration;
//    }
//
//    @Bean
//    public FilterRegistrationBean duridFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new RequestWrapperFilter());
//        HashMap<String, String> initParams = new HashMap<>();
//        // 设置忽略请求
//        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.setInitParameters(initParams);
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
//}
