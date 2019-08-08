package com.cloud.common.dynamicproxy.service;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @auth Administrator
 */
public class TicketServiceAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("after：本次服务已结束....");
    }
}
