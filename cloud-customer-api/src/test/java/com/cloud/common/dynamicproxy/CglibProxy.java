package com.cloud.common.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;


public class CglibProxy implements  MethodInterceptor{
	
	/**
	 * 创建代理对象
	 * @param target
	 * @return
	 */
	public <T> T getInstance(Object target) {
		Enhancer enhancer = new Enhancer();
		// 表示设置要代理的类
		enhancer.setSuperclass(target.getClass());
		// 回调方法 , 指 本类 intercept 方法
		//如果实现了MethodInterceptor 类这里可以传 this
		enhancer.setCallback(new MethodInterceptor() {
								 @Override
								 public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

									 if (Object.class.equals(method.getDeclaringClass())) {
										 return method.invoke(this, args);
									 }
									 String methodName = method.getName();
									 //打印日志
									 System.out.println("[before] The method " + methodName + " begins with " + (args!=null ? Arrays.asList(args) : "[]"));
									 Object result = null;
									 try{
										 //前置通知
										 result = methodProxy.invokeSuper(o, args);
										 //返回通知, 可以访问到方法的返回值
										 System.out.println(String.format("after method:%s execute", method.getName()));
									 } catch (Exception e){
										 e.printStackTrace();
										 //异常通知, 可以访问到方法出现的异常
									 }
									 //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
									 //打印日志
									 System.out.println("[after] The method ends with " + result);
									 return result;
								 }
							 });
		// 创建代理对象  
		return (T)enhancer.create();
	}

	/**
	 * 回调方法
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
		System.out.println("Before:" + method);
			Object object = proxy.invokeSuper(obj, arg);
		System.out.println("After:" + method);
		return object;
	}
}