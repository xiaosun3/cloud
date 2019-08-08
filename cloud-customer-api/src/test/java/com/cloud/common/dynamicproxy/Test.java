package com.cloud.common.dynamicproxy;

import com.cloud.common.dynamicproxy.service.*;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;

public class Test {
	/**
	 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类
	 * 　CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法（继承）
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
//		test.cglibProxy();
//		test.jdkProxy();


		HashMap hashMap = new HashMap();
		Iterator iterator =  hashMap.entrySet().iterator();
		iterator.hasNext();
		//1.针对不同的时期类型，提供不同的Advice
		Advice beforeAdvice = new TicketServiceBeforeAdvice();
		Advice afterReturningAdvice = new TicketServiceAfterReturningAdvice();
		Advice aroundAdvice = new TicketServiceAroundAdvice();

		TicketServiceImpl railwayStation = new TicketServiceImpl();

		//2.创建ProxyFactoryBean,用以创建指定对象的Proxy对象
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		//3.设置Proxy的接口
		proxyFactoryBean.setInterfaces(TicketService.class);
		//4. 设置RealSubject
		proxyFactoryBean.setTarget(railwayStation);
		//5.使用JDK基于接口实现机制的动态代理生成Proxy代理对象，如果想使用CGLIB，需要将这个flag设置成true
		proxyFactoryBean.setProxyTargetClass(true);

		//6. 添加不同的Advice

		proxyFactoryBean.addAdvice(afterReturningAdvice);
		proxyFactoryBean.addAdvice(aroundAdvice);
		proxyFactoryBean.addAdvice(beforeAdvice);
		proxyFactoryBean.setProxyTargetClass(false);
		//7通过ProxyFactoryBean生成Proxy对象
		TicketService ticketService = (TicketService) proxyFactoryBean.getObject();

		ticketService.sellTicket();


	}

	public void cglibProxy() {
		CglibProxy cglibProxy = new CglibProxy();
		HelloProxyImpl clas = cglibProxy.getInstance(new HelloProxyImpl());
		int result = clas.getConcreteMethodB(2);
		System.out.println("result:"+result);
		System.out.println(clas);

	}

	public void jdkProxy() {

		//这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
		Class<?> proxyClass = Proxy.getProxyClass(Test.class.getClassLoader(), HelloProxy.class);
		final Constructor<?> cons;
		try {
			cons = proxyClass.getConstructor(InvocationHandler.class);

			final InvocationHandler ih = new JdkProxy(new HelloProxyImpl());
			HelloProxy helloWorld = (HelloProxy) cons.newInstance(ih);
			helloWorld.getConcreteMethodB(2);

			//下面是更简单的一种写法，本质上和上面是一样的

			HelloProxy helloWorld2 = (HelloProxy) Proxy.
					newProxyInstance(Test.class.getClassLoader(),
							new Class<?>[]{HelloProxy.class},
							new JdkProxy(new HelloProxyImpl()));
			helloWorld2.getConcreteMethodB(2);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

