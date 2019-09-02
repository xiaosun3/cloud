package com.cloud;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by sunhaidi on 2019-07-29.
 */
public class TempTest {

    public static void main(String[] args) throws ParseException {
        for (int i = 0; i < 10; i++) {
            System.out.println((int)(Math.random()*100));
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.parse("2019/2/24"));
        System.out.println(format2.format(format.parse("2019/2/24")));

        System.out.println(Integer.toHexString(0x3f03));
        System.out.println(Integer.toBinaryString(0x3f03));

        System.out.println(Integer.valueOf("3f03",16).toString());

        System.out.println(System.currentTimeMillis());

        String str = "https://61.50.129.55:8080/service/third/beijing/h5/login?authAppId=520ae017b33a43f7af20f3aaedfc2b93&redirect=http%3a%2f%2f218.80.250.99%2fchangping-user-te%2fstatic%2fhtml%2fcallapp.html%3ftoken%3d%7bcode%7d&state=1";
        str= str.replace("h5/login","modifyUserinfo");
        System.out.println(URLDecoder.decode(str));

    }

    public static int pri(int a){
        System.out.println(a);
        return a;
    }
}
