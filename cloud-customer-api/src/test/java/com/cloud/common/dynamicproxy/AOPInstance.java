package com.cloud.common.dynamicproxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @auth Administrator
 */
@Component
@Aspect
public class AOPInstance {

    @Before("execution(* com.sun.boot.comtroller..*.*(..))")
    public void before(JoinPoint point){
        String classDesc =  point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        //获取所有方法参数
        for (Object o : point.getArgs()) {
            System.out.println(o);
        }
        System.out.println("之前执行~~~" + classDesc + "    " );
    }

    @After("execution(* com.sun.boot.comtroller..*.*(..))")
    public void after(){
        System.out.println("之后执行~~~");
    }


}
