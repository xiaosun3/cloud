package com.cloud.aop;

import com.cloud.annotations.AopIntercept;
import org.springframework.stereotype.Service;

/**
 * Created by sunhaidi on 2019-07-16.
 */
@Service
public class AopService {

    @AopIntercept
    public String save(){
        System.out.println("save..");
        return "aop save success!";
    }

    @AopIntercept
    public String select(String str){
        System.out.println("select.." + str);
        return "aop select success!";
    }
}
