package com.cloud.configuration;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Environment environment;

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(new MissingParameterExceptionHandler());
    }

    /**
     * 对静态资源的配置
     * 2.0之后改为extends WebMvcConfigurationSupport 不配 addResourceHandlers 导致拦截器不生效
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/webjars/");
    }

//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        Boolean isSandbox = "de".equals(getActiveProfile()) || "te".equals(getActiveProfile());
//        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//        List<Object> interceptorList = Lists.newLinkedList();
//        if (!"de".equals(getActiveProfile())) {
////            interceptorList.add(new RequestTimeInterceptor(isSandbox));
//        }
//        handlerMapping.setInterceptors(interceptorList.toArray());
//        return handlerMapping;
//    }

    /**
     * 获取当前环境
     * @return
     */
    private String getActiveProfile() {
        String[] profiles = environment.getActiveProfiles();
        if (profiles.length != 0) {
            return profiles[0];
        }
        else {
            return null;
        }
    }

    /**
     * 注入ServerEndpointExporter，
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
