package com.example.loginDemo.user.service;

import com.example.loginDemo.user.entity.Role;
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

    @Override
    public User findByProviderId(String providerId) {
        Optional<User> maybeUser = userRepository.findByProviderId(providerId);

        return maybeUser.orElse(null);
    }

    @Override
    public User create(String name, String email, String picture, String provider, String providerId, String registerId) {
        User user = User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .provider(provider)
                .providerId(providerId)
                .registerId(registerId)
                .role(Role.USER).build();
        return userRepository.save(user);
    }
}
