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
    public String googleAccessTokenUri(@RequestBody Map<String, String> body) {
        log.info("controller -> googleAccessTokenUri() called!");
        String code = body.get("code");
        log.info("controller -> googleAccessTokenUri() code: {}", code);
        String accessToken = oAuthService.requestAccessToken(code);
        log.info("controller -> googleAccessTokenUri() accessToken: {}", accessToken);
        return accessToken;
    }

    @PostMapping("google/userinfo")
    public ResponseEntity<String> googleUserinfoUri(@RequestBody Map<String, String> body) {
        log.info("controller -> googleUserinfoUri() called!");
        String accessToken = body.get("accessToken");
        log.info("controller -> googleUserinfoUri() accessToken: {}", accessToken);
        ResponseEntity<String> response = oAuthService.requestUserinfo(accessToken);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("google/redis-user-token")
    public ResponseEntity<String> saveUserTokenToRedis(@RequestBody Map<String, String> body) {
        log.info("controller -> saveUserTokenToRedis() called!");
        String providerId = body.get("id");
        log.info("controller -> saveUserTokenToRedis() providerId: {}", providerId);
        User user = userService.findByProviderId(providerId);

        String response = redisService.setUserTokenToRedis(user);
        return ResponseEntity.ok(response);
    }
}
