package com.cloud.redis;

import com.cloud.configuration.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sunhaidi on 2019-03-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class RedisTest {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Test
    public void save(){
        redisTemplate.opsForValue().set("sunhd","123");
    }
}
