package com.example.demo.dto;

import lombok.Builder;

@Builder
public class JwtTokenDTO {
    private String result;
    private String accessToken;
    private String refreshToken;
}
