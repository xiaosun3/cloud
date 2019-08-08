package com.cloud.threadtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by sunhaidi on 2019-05-16.
 */
@RunWith(SpringRunner.class)
public class ThreadPool {

    public static void main(String[] args) {

    }

    @Test
    public void testThreadJoin() throws InterruptedException {
        System.out.println("main thread start");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " start");
            });
            thread.start();
            thread.join();
        }

        System.out.println("main thread end");
    }

    /**
     * 假设有一个很耗时的返回值需要计算，并且这个返回值不是立刻需要的话，那么就可以使用 Future + Callable，
     * 用另一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过Future得到，岂不美哉
     */
    @Test
    public void testCallable(){
        ThreadPoolTaskExecutor poolExecutor = buildThreadPoolExecutor();
        Future<Integer> result = poolExecutor.submit(new ThreadTask.ThreadCallable());
        Future<Integer> result2 = poolExecutor.submit(() -> 222);
        poolExecutor.shutdown();

        System.out.println("主线程业务 start");

        try {
            //result.get() 会阻塞当前线程,等待子线程完成。
            System.out.println("task运行结果" + result2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");
    }

    /**
     * 验证线程池在等待队列满了之后才会创建最大线程数
     */
    @Test
    public void testThreadPool() {
        ThreadPoolTaskExecutor poolExecutor = buildThreadPoolExecutor();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            poolExecutor.execute(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + ":" + finalI);
                    }
            );
        }

    }

    public static ThreadPoolTaskExecutor buildThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(3);
        //配置最大线程数
        executor.setMaxPoolSize(6);
        //配置队列大小
        executor.setQueueCapacity(10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("test_thread_pool ");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
