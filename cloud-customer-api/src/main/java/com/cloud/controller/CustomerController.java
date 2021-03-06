package com.cloud.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cloud.configuration.RefreshDto;
import com.cloud.service.goods.GoodsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerController {
    @Autowired
    GoodsService goodsService;
    //    @Autowired
//    CustomerService customerService;
    @Autowired
    RefreshScope refreshScope;
    @Autowired
    RefreshDto refreshDto;

    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    @RequestMapping(path = "/refreshBean", method = RequestMethod.GET)
    public String refreshBean(@RequestParam(required = false) String name) {
        if (StringUtils.isNotBlank(name)) {
            refreshScope.refresh(name);
        } else {
            refreshScope.refreshAll();
        }
        System.out.println();
        System.out.println(String.format("befor:%s after:%s", username, refreshDto.getName()));
        return "refreshBean";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(@RequestParam String userId) {
        //如果goodsService里面的方法有标注@Transactional 那么获取到的class就是代理类对象
        System.out.println("goodsService class:" + goodsService.getClass());
        System.out.println("customer userId " + userId);
        return userId;
    }

    @RequestMapping(path = "/hystrixTest", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "serviceFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Object hystrixTest() {
//            customerService.saveCustomer(new Customer("孙海迪", "1"));
        try {
            Thread.sleep(RandomUtils.nextInt(500, 1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String serviceFallback(Throwable throwable) {
        return "error,Hystrix default value";
    }


    /**
     * 带超时时间的异步请求 通过WebAsyncTask自定义客户端超时间
     *
     * @return
     */
    @GetMapping("/world")
    public WebAsyncTask<String> worldController() {
        System.out.println(Thread.currentThread().getName() + " 进入helloController方法");

        // 3s钟没返回，则认为超时
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {

            @Override
            public String call() {
                System.out.println(Thread.currentThread().getName() + " 进入call方法");
                String say = "sayHello";
                System.out.println(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;
            }
        });
        System.out.println(Thread.currentThread().getName() + " 从helloController方法返回");

        webAsyncTask.onCompletion(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            }
        });

        webAsyncTask.onTimeout(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " onTimeout");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException("调用超时");
            }
        });
        return webAsyncTask;
    }
}
