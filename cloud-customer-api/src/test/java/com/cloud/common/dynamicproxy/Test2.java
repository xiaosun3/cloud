package com.cloud.common.dynamicproxy;


import java.util.ArrayList;
import java.util.List;

/**
 * @auth Administrator
 */
public class Test2 implements  Cloneable{
    public static List<String> map = new ArrayList<String>();
    public static void main(String[] args) {
        List<String> map2 = new ArrayList<String>();
        while (true){
            map.add("孙海迪");
            map2.add("海迪孙");
            try {
//                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        String str = new String("孙");
//        String str2 = new String("孙");
//        System.out.println(ClassLoader.getSystemClassLoader());
//        System.out.println(Thread.currentThread().getContextClassLoader());
//        System.out.println(String.class.getClassLoader());
//        System.out.println(Test2.class.getClassLoader());
//
//        TreeMap<String,String> map = new TreeMap<String, String>();
//        map.put("b","b");
//        map.put("a","b");
//
//                for(String  st2r : map.keySet()){
//                    System.out.println(st2r);
//                }
//
       }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public String getSs(String str) {
        return str + "+";
    }


}
