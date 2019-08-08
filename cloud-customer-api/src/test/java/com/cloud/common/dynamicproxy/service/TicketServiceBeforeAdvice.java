package com.cloud.common.dynamicproxy.service;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @auth Administrator
 */
public class TicketServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(" before: 欢迎光临代售点....");
    }
}
