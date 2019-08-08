package com.cloud.common.dynamicproxy;

public class HelloProxyImpl implements HelloProxy {
    @Override
    public String getConcreteMethodA(String parameter){  
        System.out.println("ConcreteMethod A ... " + parameter);  
        return parameter;
    }

    @Override
    public int getConcreteMethodB(int n){  
        System.out.println("ConcreteMethod B ... " + n);  
        return n+10;  
    }  
}  