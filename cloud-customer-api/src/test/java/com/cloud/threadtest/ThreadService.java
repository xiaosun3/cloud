package com.cloud.threadtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sunhaidi on 2019-04-10.
 */
public class ThreadService {
    private static final Logger log = LoggerFactory.getLogger(ThreadService.class);

    public void save() {
        synchronized (ThreadService.class) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("impl save");
            System.out.println("impl save");
        }
    }

    public void select() {
        synchronized (ThreadService.class) {
            log.info("impl select");
            System.out.println("impl select");
        }
    }

    public synchronized void syn1() {
        System.out.println("syn1");

    }

    public synchronized void syn2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("syn2");
    }

    private static String message = "hello";
    private static AtomicReference<String> atomicReference;

    /**
     * cas 原子操作测试
     */
    public static void atomicCas() {

        atomicReference = new AtomicReference<String>(message);
        new Thread("Thread 1") {
            public void run() {
                boolean a = atomicReference.compareAndSet("2", "Thread 1");
                System.out.println(a);
                message = message.concat("-Thread 1!");
            }
        }.start();
//        Thread.sleep(50);
        System.out.println("Message is: " + message);
        System.out.println("Atomic Reference of Message is: " + atomicReference.get());

    }
}
