package com.example.loginDemo.oauth.service;

import com.example.loginDemo.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String setUserTokenToRedis(User user) {
        if (user == null) {
            return "";
        }

        String userToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(userToken, String.valueOf(user.getId()));
        return userToken;
    }
}
