package com.example.loginDemo.oauth.service;

import com.example.loginDemo.oauth.service.response.AccessTokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
//        String encodedCode = URLEncoder.encode(code, StandardCharsets.UTF_8);
//        log.info("encodedCode: {}", encodedCode);

        String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);

        Map<String, String> requestBody = Map.of(
                "code", decodedCode,
                "client_id", clientId,
                "client_secret", clientSecret,
                "redirect_uri", loginRedirectUri,
                "grant_type", "authorization_code"
        );

        HttpEntity<Map<String, String>> googleTokenRequest = new HttpEntity<>(requestBody);

        ResponseEntity<String> response = restTemplate.exchange(
                tokenRedirectUri,
                HttpMethod.POST,
                googleTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenResponse accessTokenResponse = null;

        try {
            accessTokenResponse = objectMapper.readValue(response.getBody(), AccessTokenResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("access_token_response: {}", accessTokenResponse);

        return accessTokenResponse.getAccess_token();
    }

    @Override
    public ResponseEntity<String> requestUserinfo(String accessToken) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(userinfoRedirectUri + accessToken));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        log.info("requestUserinfo responseEntity: {}", responseEntity.getBody());
//        Map response = restTemplate.getForObject(
//                userinfoRedirectUri + accessToken, Map.class);

        return responseEntity;
    }
}
