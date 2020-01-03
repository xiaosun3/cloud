package com.cloud;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunhaidi on 2019-07-29.
 */
public class TempTest {

    public static void main(String[] args) throws ParseException, InterruptedException {
        System.out.println(System.currentTimeMillis());

        ArrayList list = new ArrayList();
        list.add("2_1");
        list.add("2_2");
        list.add("2_3");
        list.add("2_4");
        list.add("2_5");
        list.add("2_1");

        list.get(1);
        LinkedList list2 = new LinkedList();
        list2.add("");
        list2.remove("");

//        list.stream().filter()

        System.out.println("main thread start");
        AtomicInteger a = new AtomicInteger();
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(() -> {
                while (true){
//                    a.getAndIncrement();
//                    try {
//                        System.out.println(Thread.currentThread().getName() + " sleep");
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
            thread.start();

            System.out.println("主线程开始暂停");
            Thread.sleep(1000);

            System.out.println("主线程结束暂停");
            System.out.println(thread.isInterrupted());
            thread.interrupt();
            System.out.println(thread.isInterrupted());
//            System.out.println("waiting");
        }

        System.out.println("main thread end");



    }

    public String ss(){
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            return  "1";
        }
        return "3";
    }

    public static int pri(int a){
        System.out.println(a);
        return a;
    }

    public static <T extends Tem<T>, E> List<T> splitBrandFile(T t, Class<T> clazz, String... fields) {


        return null;
    }

    static class Tem<T>{

    }
}
