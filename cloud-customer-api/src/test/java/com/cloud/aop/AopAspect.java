package com.cloud.aop;

import com.alibaba.fastjson.JSON;
import com.cloud.annotations.AopIntercept;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by sunhaidi on 2019-07-16.
 */
@Aspect
@Component
public class AopAspect {
    private static final Logger logger = LoggerFactory.getLogger(AopAspect.class);
    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(aopIntercept)")
    public void pointcut(AopIntercept aopIntercept){

    }

    @Before("pointcut(aopIntercept)")
    public void before(JoinPoint joinPoint, AopIntercept aopIntercept){
        System.out.println("before...");
//        redisTemplate.opsForValue().increment("before");
//        joinPoint.
    }

    @After("pointcut(aopIntercept)")
    public void after(JoinPoint joinPoint, AopIntercept aopIntercept){
        System.out.println("after...");
//        redisTemplate.opsForValue().increment("after");
    }

    @Around("pointcut(aopIntercept)")
    public void Around(ProceedingJoinPoint joinPoint, AopIntercept aopIntercept) throws Throwable {
        System.out.println("Around...");
        Object result= joinPoint.proceed();
        logger.info("result:" + JSON.toJSON(result));
//        redisTemplate.opsForValue().increment("after");
    }

    @AfterReturning(returning="rvt", pointcut = "pointcut(aopIntercept)")
    public Object AfterExec(JoinPoint joinPoint, Object rvt, AopIntercept aopIntercept){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //IP地址
        logger.info("rvt:"+(String) JSON.toJSON(rvt));
        return rvt;
    }
}
