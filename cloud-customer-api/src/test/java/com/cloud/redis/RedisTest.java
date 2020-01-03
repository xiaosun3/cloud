package com.cloud.redis;

import com.cloud.configuration.Application;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 2
 * Created by sunhaidi on 2019-03-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class RedisTest {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Autowired
    JedisCluster jedisCluster;
    @Autowired
    JedisPool jedisPool;

    @Test
    public void save() throws InterruptedException {

        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            fixedExecutorService.execute(
                    () -> {
//                        jedisCluster.setex("shd" + finalI + "",300,"shd" + finalI);

                        jedisCluster.incr("shd2");
                        System.out.println("i:" + finalI);//
                    }
            );
        }

        Thread.currentThread().join();

    }

    @Test
    public void jedisCluster(){

        jedisCluster.set("sunhd","sunhd_value");
        System.out.println(jedisCluster.get("sunhd"));

        System.out.println(redisTemplate.opsForValue().get("sunhd"));

        while (true){

        }
    }

    /**
     *
     */
    @Test
    public void testSetNx(){
        String uuid = UUID.randomUUID().toString();
        String key = "shdset_key";
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";

        //获取锁，如果key不存在就插入，插入返回：OK   未插入返回：null
        jedisCluster.set(key,uuid,"NX","EX",300);
        //执行lua脚本释放锁
        jedisCluster.eval(luaScript, Collections.singletonList(key), Collections.singletonList(uuid)).equals(1L);

    }

    /**
     * 订阅频道，有消息在 TestPubSub 类获取
     */
    @Test
    public void subTest(){
        jedisCluster.subscribe(new TestPubSub(), "channelTest");
        while (true){

        }
    }

    /**
     * 发布消息
     */
    @Test
    public void pubTest(){
        Long l = jedisCluster.publish("channelTest","channelTest msg");
        System.out.println("lll:" + l);

    }
}
