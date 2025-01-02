package com.example.loginDemo.oauth.service;

import com.example.loginDemo.user.entity.User;

public interface RedisService {
    String setUserTokenToRedis(User user);
    String getValueByKey(String key);
    void deleteKey(String key);
}
