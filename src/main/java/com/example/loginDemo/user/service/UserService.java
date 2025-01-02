package com.example.loginDemo.user.service;

import com.example.loginDemo.user.entity.User;

public interface UserService {
    User findByEmail(String email);
    User findByProviderId(String providerId);
    User create(String name, String email, String picture, String provider, String providerId, String registerId);
}
