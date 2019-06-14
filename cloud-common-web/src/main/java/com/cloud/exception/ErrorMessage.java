package com.cloud.exception;//package com.cloud.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorMessage {

    private final int code;
    private final String msg;

    public ErrorMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorMessage(String msg) {
        this.code = 1000;
        this.msg = msg;
    }

    public ErrorMessage(BaseException ex) {
        this.code = ex.code();
        this.msg = ex.msg();
    }


    public static void main(String[] args) {
        String a = new String();
        String b = new String();

        Map<String, String> map = new HashMap<String, String>();

        map.put(a, "aa");

        System.out.println(map.get(b));

        System.out.println(a.hashCode() == b.hashCode());
    }
}