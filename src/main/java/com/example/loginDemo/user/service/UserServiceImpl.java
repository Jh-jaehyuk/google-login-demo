package com.example.loginDemo.user.service;

import com.example.loginDemo.user.entity.User;
import com.example.loginDemo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.example.loginDemo.user")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        Optional<User> maybeUser = userRepository.findByEmail(email);

        return maybeUser.orElse(null);
    }
}
