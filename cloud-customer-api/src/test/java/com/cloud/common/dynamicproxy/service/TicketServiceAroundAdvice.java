package com.cloud.common.dynamicproxy.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @auth Administrator
 */
public class TicketServiceAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before :begin....");
        Object returnValue = methodInvocation.proceed();
        System.out.println("END:.....");
        return returnValue;
    }
}
