package com.example.weiboclone.jwt;

public interface JwtProperties {
    String SECRET = "2조"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 1000*60*30; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
