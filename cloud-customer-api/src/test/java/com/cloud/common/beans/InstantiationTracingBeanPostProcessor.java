package com.cloud.common.beans;//package com.sun.boot.common.beans;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * spring 启动打印所有 spring IOC bean
// */
//@Component
//public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {
//
//    // simply return the instantiated bean as-is
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        return bean; // we could potentially return any object reference here...
//    }
//    //在创建bean后输出bean的信息
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("\n test Bean '" + beanName + "' created : " + bean.toString()+"\n");
//        return bean;
//    }
//
//}
