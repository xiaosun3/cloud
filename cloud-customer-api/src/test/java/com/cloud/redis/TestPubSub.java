package com.cloud.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by sunhaidi on 2019-08-30.
 */
public class TestPubSub extends JedisPubSub {


    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("onMessage channel:%s message:%s",channel,message));
        super.onMessage(channel, message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(String.format("onPMessage pattern:%s channel:%s message:%s",pattern,channel,message));
        super.onPMessage(pattern, channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        super.onPUnsubscribe(pattern, subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        super.onPSubscribe(pattern, subscribedChannels);
    }

    @Override
    public void onPong(String pattern) {
        super.onPong(pattern);
    }
}
