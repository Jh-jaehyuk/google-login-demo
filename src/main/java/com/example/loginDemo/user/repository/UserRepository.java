package com.example.loginDemo.user.repository;

import com.example.loginDemo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRegisterId(String registerId);
    Optional<User> findByEmail(String email);
}
