package com.example.loginDemo.oauth.service.response;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private String access_token;
    private int expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;
    private String id_token;
}
