package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}") /*application.yaml에 설정해놓은 key*/
    private final String key;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Key getSecretKey(String key) {
        byte[] KeyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    /*Access Token 생성 함수*/
    public String generateAccessToken(Authentication authentication) {  /*Authentication 클래스를 인자로 받음*/
        /*사용자 정보 갖고오기*/
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentTime = (new Date()).getTime();

        /*JWT Payload에 들어갈 정보*/
        Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setSubject(userDetails.getUsername())
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
    public String generateRefreshToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long currentTime = (new Date()).getTime();

        Claims claims = Jwts.claims()
                .setSubject("refresh_token")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTime+(3 * 24 * 60 * 60 * 1000)));

        /*토큰 생성*/
        String refreshToken = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return refreshToken;
    }


    /*토큰으로부터 사용자 정보 객체 갖고오는 함수*/
    public String getUsernameFromToken(String jwt) {
        String UserName = Jwts.parserBuilder().setSigningKey(getSecretKey(key))
                .build()
                .parseClaimsJws()
    }
}
