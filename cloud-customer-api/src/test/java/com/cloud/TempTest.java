package com.cloud;

/**
 * Created by sunhaidi on 2019-07-29.
 */
public class TempTest {

    public static void main(String[] args) {

        System.out.println(Integer.toHexString(0x3f03));
        System.out.println(Integer.toBinaryString(0x3f03));

        System.out.println(Integer.valueOf("3f03",16).toString());

        System.out.println(System.currentTimeMillis());

        int i = 0;
        for (pri(i++),pri(++i); i < 6 && pri(i++) < 5; pri(++i)) {
            pri(0);
        }
//        pri(++i);
//        System.out.println(i);


        switch (i){
            case 3:
                System.out.println(3);
            return;

        }

        Integer o = null;
        Integer o1 = o;
        o = new Integer(3);
        System.out.println(o == o1);
        System.out.println(o);
        System.out.println(o1);

    }

    public static int pri(int a){
        System.out.println(a);
        return a;
    }
}
