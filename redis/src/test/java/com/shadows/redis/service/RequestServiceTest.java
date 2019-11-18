package com.shadows.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RequestServiceTest {
    @Autowired
    RequestService requestService;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void login() {
        HttpClientContext loginResult = null;
        Map<String, String> params = new HashMap<>();
        params.put("courseOpenId", "2kuaarcqvrfffpnio1rq8a");
        params.put("schoolCode", "060");
        try {
            loginResult = requestService.login("342222199009055308", "055308");
            String result = requestService.get(loginResult, "/studentStudio/", params);
            log.info("get result:{}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}