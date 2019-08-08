package com.cloud;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by sunhaidi on 2019-04-25.
 */
public class RestTemplateDemo {

    public static void main(String[] args) {
//        ImmutableMap.of("","","","");
//        System.out.println(System.currentTimeMillis());
//        String str = "{  \"accountId\": \"\",  \"gwUrl\": \"\",  \"mobile\": \"\",  \"registerId\": \"%s\",  \"url\": \"%s\"}";
//        System.out.println(String.format(str,"1","2"));
//        System.out.println(queryUserInfo("http://10.1.64.194/healthCloud-esb/esb/d1610d81ae824e4ca5ca5697f38b541f?account=8a81c01a4f29ba25014faae99f9b006e"));
//        System.out.println(registerUser("http://10.1.64.194/healthCloud-esb/esb/251059ea435a4abeba06868bf457d5d0"));
//          String url = "http://218.80.250.99/neohealthcloud-diabetes-app-user-h5-te/healthDiseaseApi/api/report/screening?uid=ff8080815a5a5c9f015c1551195e0061";
//          String url = "http://10.1.64.195/healthCloud-esb/esb/be3c8340633b4a5da042bf3f5b0c4a86";
//          String result = requestEsb(url,"ff8080815a5a5c9f015c1551195e0061","/api/report/screening");
//          System.out.println(result);
        queryUserInfo("http://10.1.64.194/healthCloud-esb/esb/111395239f424cf9991ec26f619e265b");
    }

    public static String requestEsb(String esbUrl, String registerId, String url) {
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("GBK"));
        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("authKey", "bf845443b36f4823be891811787553eb");
        requestHeaders.add("timestamp", String.valueOf(System.currentTimeMillis()));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);

        String str = "{  \"accountId\": \"\",  \"gwUrl\": \"\",  \"mobile\": \"\",  \"registerId\": \"%s\",  \"url\": \"%s\"}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(String.format(str, registerId, url), requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(esbUrl, HttpMethod.POST, requestEntity, String.class);
        String body = response.getBody();
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            if (0 != jsonObject.getIntValue("code")) {
                return null;
            }
            body = jsonObject.getJSONObject("data").getJSONObject("demoData").toString();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return body;
    }

    public static Integer queryUserInfo(String url) {
//        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("GBK"));
//        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
        System.out.println(System.currentTimeMillis());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("authKey", "f1ddef0aa2534d80b527a79f92f9060d");
        requestHeaders.add("timestamp", String.valueOf(System.currentTimeMillis()));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);

        HttpEntity requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String body = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);

        System.out.println(Integer.MAX_VALUE);
        return jsonObject.getIntValue("data");
    }

    public static String registerUser(String url) {
//        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("GBK"));
//        RestTemplate restTemplate = new RestTemplateBuilder().additionalMessageConverters(m).build();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("authKey", "3ecea27d64f54379a2fff02fda41b7c9");
        requestHeaders.add("timestamp", String.valueOf(System.currentTimeMillis()));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);

        url+= String.format("?username=%s&mobile=%s&email=%s&password%s","","18075627539","","");

        HttpEntity requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String body = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        System.out.println(":"+jsonObject.getIntValue("code"));

        return body;
    }
}
