//package com.cloud.controller.websoket;
//
//import com.google.common.collect.ImmutableMap;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//import javax.websocket.Session;
//import java.util.HashMap;
//
///**
// * Created by sunhaidi on 2019-04-18.
// * 基于 Spring 使用 WebSocket 的三种方式：https://www.zifangsky.cn/1355.html    https://www.cnblogs.com/yueshutong/p/9978021.html
// * 使用 Java   提供的 @ServerEndpoint 注解实现
// * 使用 Spring 提供的低层级 WebSocket API实现
// * 使用 STOMP  消息实现
// */
//@Controller
//public class WebsoketServerController {
//
//    private boolean firstFlag = true;
//    private Session session;
//    private String userName;
//    // 记录此次聊天室的服务端有多少个连接:有用户发送消息，需要把所有连接都开通，把此消息发送给其他所有在线的用户
//    // key代表此次客户端的session，value代表此次连接对象
//    private static final HashMap<String, Object> connectMap = new HashMap<String, Object>();
//    // 保存所有的用户昵称信息
//    // key是sessionId，value：用户名
//    private static final HashMap<String, String> userMap = new HashMap<String, String>();
//
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Object hellgreetingo(String message) {
//        return message;
//
//    }
//}
