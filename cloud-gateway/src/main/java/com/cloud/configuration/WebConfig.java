//package com.cloud.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    private Environment environment;
//
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
////        exceptionResolvers.add(new MissingParameterExceptionHandler());
//    }
//
//    /**
//     * 对静态资源的配置
//     * 2.0之后改为extends WebMvcConfigurationSupport 不配 addResourceHandlers 导致拦截器不生效
//     * @param registry
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**");
//
//    }
//    /**
//     * 获取当前环境
//     * @return
//     */
//    private String getActiveProfile() {
//        String[] profiles = environment.getActiveProfiles();
//        if (profiles.length != 0) {
//            return profiles[0];
//        }
//        else {
//            return null;
//        }
//    }
//
//}
