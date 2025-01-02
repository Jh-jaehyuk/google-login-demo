package com.example.loginDemo.oauth.service;

import com.example.loginDemo.user.entity.User;

public interface RedisService {
    String setUserTokenToRedis(User user);
}
