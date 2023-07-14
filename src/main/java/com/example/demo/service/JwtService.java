package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final String key = "SECRET_KEY";
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*Access Token 생성 함수*/
    public String generateAccessToken(Authentication authentication) {  /*Authentication 클래스를 인자로 받음*/
        long currentTime = (new Date()).getTime();

        /*JWT Payload에 들어갈 정보*/
        Claims claims = Jwts.claims()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTime+(3 * 24 * 60 * 60 * 1000)));

        /*토큰 생성*/
        String accessToken = Jwts.builder()
                /*Header*/
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                /*Payload*/
                .setClaims(claims)
                /*Signature*/
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return accessToken;
    }

    /*Refresh Token 발급*/
    public String generateRefreshToken(Long memberId, String memberType) {
        long currentTime = (new Date()).getTime();

        Claims claims = Jwts.claims()
                .setSubject("refresh_token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTime+(14 * 24 * 60 * 60 * 1000))); /*토큰 만료일 -> 2주로 설정*/
        claims.put("id", memberId);
        claims.put("role", memberType);

        /*토큰 생성*/
        String refreshToken = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        /*Hashing 처리*/
        String hashRefreshtoken = bCryptPasswordEncoder.encode(refreshToken);

        /*DB 저장*/
        memberService.addRefreshToken(memberId, hashRefreshtoken);

        return hashRefreshtoken;
    }
}
