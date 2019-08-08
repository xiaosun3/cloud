package com.cloud.threadtest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by sunhaidi on 2019-07-09.
 */
public class ThreadCountDownLatch {


    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch downLatch = new CountDownLatch(count);
        Executor executor = Executors.newFixedThreadPool(count);
        System.out.println("main start");
        for (int i = 0; i < count; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 准备");
                downLatch.countDown();
            });
        }
        downLatch.await();
        System.out.println("main end");


    }


}
