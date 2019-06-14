package com.cloud.controller.websoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunhaidi on 2019-04-18.
 */
@RestController
@ServerEndpoint("/websocket/chat")
public class WebsoketServer {

    private static final Logger log = LoggerFactory.getLogger(WebsoketServer.class);
    private static ConcurrentHashMap hashMap = new ConcurrentHashMap();

    @OnOpen
    public void openSession(@PathParam("uid") String uid , Session session) {
        String message = "欢迎用户[" + 1 + "] 来到聊天室！";
        log.info(message);
        WebSocketUtils.sendMessageAll(message);
        hashMap.put(uid, session);

    }

    @OnMessage
    public void onMessage(String message) {
        log.info(message);
        WebSocketUtils.sendMessageAll("用户[" + 1 + "] : " + message);
    }

    @OnClose
    public void onClose(@PathParam("uid") String uid, Session session) {
        //当前的Session 移除
        hashMap.remove(uid);
        //并且通知其他人当前用户已经离开聊天室了
        WebSocketUtils.sendMessageAll("用户[" + uid + "] 已经离开聊天室了！");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }


    @GetMapping("/chat-room/{sender}/to/{receive}")
    public void onMessage(@PathVariable("sender") String sender, @PathVariable("receive") String receive, String message) {
        WebSocketUtils.sendMessage(WebSocketUtils.LIVING_SESSIONS_CACHE.get(receive), "[" + sender + "]" + "-> [" + receive + "] : " + message);
    }
}
