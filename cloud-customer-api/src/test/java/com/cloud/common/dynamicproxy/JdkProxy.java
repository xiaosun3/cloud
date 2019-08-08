package com.cloud.common.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class JdkProxy implements InvocationHandler{

	private Object target;

	public JdkProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("method :"+ method.getName()+" is invoked!");
		return method.invoke(target, args);
	}
}