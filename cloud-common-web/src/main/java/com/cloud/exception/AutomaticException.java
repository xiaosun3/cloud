package com.cloud.exception;

/**
 * Created by nick on 2016/6/12.
 * @author nick
 */
public class AutomaticException extends BaseException{

    public AutomaticException(){
        super(1004, "用户不存在");
    }
}
