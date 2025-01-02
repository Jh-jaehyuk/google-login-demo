package com.example.loginDemo.oauth.service.response;

import lombok.Data;

@Data
public class UserinfoResponse {
    private String issued_to;
    private String audience;
    private String scope;
    private int expires_in;
    private String access_type;
}
