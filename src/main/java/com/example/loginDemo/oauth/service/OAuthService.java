package com.example.loginDemo.oauth.service;

import com.example.loginDemo.oauth.service.response.AccessTokenResponse;
import com.example.loginDemo.oauth.service.response.UserinfoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OAuthService {
    String googleLoginAddress();
    String requestAccessToken(String code);
    ResponseEntity<String> requestUserinfo(String accessToken);
}
