package com.example.demo.service;

import com.example.demo.type.MemberType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final Key key;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*Access Token 생성 함수*/
    public String generateAccessToken(Long memberId, MemberType memberType) {
        long now = (new Date()).getTime();

        /*JWT Payload에 들어갈 정보*/
        Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(new Date()) /*토큰 생성일*/
                .setExpiration(new Date(now+1000)); /*토큰 만료일*/
        claims.put("id", memberId);
        claims.put("role", memberType);

        /*토큰 생성*/
        String accessToken = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return accessToken;
    }

    /*Refresh Token 발급*/
    public String generateRefreshToken(Long memberId, MemberType memberType) {
        long now = (new Date()).getTime();

        /*JWT Payload에 들어갈 정보*/
        Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(new Date()) /*토큰 생성일*/
                .setExpiration(new Date(now+1000)); /*토큰 만료일*/
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


        /*DB 저장*/

        return refreshToken;
    }


}
