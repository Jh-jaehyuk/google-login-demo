package com.example.loginDemo.oauth.service;

import com.example.loginDemo.oauth.dto.AccessTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String loginRedirectUri;
    @Value("${spring.security.oauth2.client.registration.google.token-redirect-uri}")
    private String tokenRedirectUri;
    @Value("${spring.security.oauth2.client.registration.google.userinfo-redirect-uri}")
    private String userinfoRedirectUri;

    @Override
    public String googleLoginAddress() {
        String redirectUri = String.format(
                "https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code&client_id=%s.apps.googleusercontent.com&redirect_uri=%s",
                clientId, loginRedirectUri);
        log.info("loginRedirectUri: {}", loginRedirectUri);
        log.info("redirectUri: {}", redirectUri);
        return redirectUri;
    }

    @Override
    public String requestAccessToken(String code) {
        String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        Map<String, String> requestBody = Map.of(
                "code", decodedCode,
                "client_id", clientId,
                "client_secret", clientSecret,
                "redirect_uri", loginRedirectUri,
                "grant_type", "authorization_code"
        );

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Map<String, String>> googleTokenRequest = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                tokenRedirectUri,
                HttpMethod.POST,
                googleTokenRequest,
                String.class
        );
        log.info("requestUserinfo responseEntity: {}", responseEntity.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenDto accessTokenDto = null;
        try {
            accessTokenDto = objectMapper.readValue(responseEntity.getBody(), AccessTokenDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return accessTokenDto.getAccess_token();
    }

    @Override
    public ResponseEntity<String> requestUserinfo(String accessToken) {
        String decodedToken = URLDecoder.decode(accessToken, StandardCharsets.UTF_8);
        log.info("decodedToken: {}", decodedToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        RequestEntity<Void> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(userinfoRedirectUri));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        log.info("requestUserinfo responseEntity: {}", responseEntity.getBody());

        return responseEntity;
    }
}
