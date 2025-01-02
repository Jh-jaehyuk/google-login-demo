package com.example.loginDemo.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String picture;
    private String registerId;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Google 로그인 시, google이 들어감
    private String provider;

    // Google 로그인 한 유저의 고유 ID가 들어감
    private String providerId;
}
