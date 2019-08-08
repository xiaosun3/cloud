package com.cloud.threadtest;

import java.util.concurrent.Callable;

/**
 * Created by sunhaidi on 2019-05-16.
 */
public class ThreadTask implements Runnable {
    public String value;

    public ThreadTask() {

    }

    public ThreadTask(String value) {
        this.value = value;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("value:" + value);
    }


    public static class ThreadCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("start threadCallable");
            System.out.println(Thread.currentThread().getName() + " call return ?");

            return 1000;
        }
    }
}
