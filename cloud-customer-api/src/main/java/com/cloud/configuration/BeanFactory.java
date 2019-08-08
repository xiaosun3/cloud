package com.cloud.configuration;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cloud.filters.RequestWrapperFilter;
import com.cloud.servlet.MyServelet;
import com.google.common.collect.ImmutableList;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class BeanFactory {

    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    /**
     * @RefreshScope 标识的bean 可以使用 refreshScope.refresh("beanName") 刷新bean
     * @return
     */
    @Bean("refreshDto")
    @RefreshScope
    public RefreshDto refreshDto() {
        RefreshDto refreshDto = new RefreshDto();
        refreshDto.setName(username);
        return refreshDto;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean SignFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("RequestWrapperFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

    @Bean(name = "publicExecutor")
    public Executor publicExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(30);
        //配置队列大小
        executor.setQueueCapacity(100000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("public_executor_");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean srb = new ServletRegistrationBean();
////        srb.setName("gateway2");
////        srb.addInitParameter("targetUri", targetUri);
////        srb.addInitParameter(ProxyServlet.P_LOG, "false");
//        srb.setServlet(new MyServelet());
//        srb.setUrlMappings(ImmutableList.of("/"));
//        return srb;
//    }

    /**
     * hystrix Servlet 用于监控 hystrix 接口的监控
     * 向监控中心Dashboard发送stream消息
     */
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("hystrixServlet");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean duridFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestWrapperFilter());
        HashMap<String, String> initParams = new HashMap<>();
        // 设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
