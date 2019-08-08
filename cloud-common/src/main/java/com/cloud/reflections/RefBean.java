package com.cloud.reflections;

/**
 * Created by sunhaidi on 2019-07-04.
 */

public class RefBean {

    public String name;
    public String age;


    static {
        System.out.println("static");
    }

    public String toString(){
        return name + ":" + age;
    }
}
