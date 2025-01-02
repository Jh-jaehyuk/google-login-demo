package com.example.loginDemo.user.service;

import com.example.loginDemo.user.entity.User;

public interface UserService {
    User findByEmail(String email);
}
