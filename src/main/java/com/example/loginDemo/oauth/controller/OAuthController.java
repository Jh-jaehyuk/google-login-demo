package com.example.loginDemo.oauth.controller;

import com.example.loginDemo.oauth.service.OAuthService;
import com.example.loginDemo.oauth.service.RedisService;
import com.example.loginDemo.user.entity.User;
import com.example.loginDemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final OAuthService oAuthService;
    private final UserService userService;
    private final RedisService redisService;

    @GetMapping("/google")
    public ResponseEntity<String> googleOauthUri() {
        log.info("controller -> googleOauthUri() called!");
        String redirectUri = oAuthService.googleLoginAddress();
        log.info("controller -> googleOauthUri() redirectUri: {}", redirectUri);
        return ResponseEntity.ok(redirectUri);
    }

    @PostMapping("/google/access-token")
    public ResponseEntity<String> googleAccessTokenUri(@RequestBody String code) {
        log.info("controller -> googleAccessTokenUri() called!");
        log.info("controller -> googleAccessTokenUri() code: {}", code);
        String accessToken = oAuthService.requestAccessToken(code);
        log.info("controller -> googleAccessTokenUri() accessToken: {}", accessToken);
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("google/userinfo")
    public ResponseEntity<String> googleUserinfoUri(@RequestBody String accessToken) {
        log.info("controller -> googleUserinfoUri() called!");
        log.info("controller -> googleUserinfoUri() accessToken: {}", accessToken);
        ResponseEntity<String> response = oAuthService.requestUserinfo(accessToken);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("google/redis-user-token")
    public ResponseEntity<String> saveUserTokenToRedis(@RequestBody String email) {
        User user = userService.findByEmail(email);

        String response = redisService.setUserTokenToRedis(user);
        return ResponseEntity.ok(response);
    }
}
