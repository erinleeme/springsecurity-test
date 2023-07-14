package com.example.demo.controller;

import com.example.demo.dto.JwtTokenDTO;
import com.example.demo.service.JwtTokenProvider;
import com.example.demo.service.MemberService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /*로그인 함수*/
    @ResponseBody
    @Validated
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginCheck(@RequestParam @Email String email, @RequestParam String password) {
        /*1. 사용자로부터 받은 email과 password 전달*/
        /*2. UserPasswordAuthenticationToken 발급*/
        /*3. 발급된 UserPasswordAuthenticationToken 으로 AuthenticationManger로 보내져 사용자 유효성 검사 진행*/
        /*3-1. Email로 사용자 조회*/
        /*3-2. 아이디랑 비밀번호 일치하는지 확인*/
        /*4. 인증에 성공하면 성공한 인증객체인 Authentication 생성*/
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        /*5. Authentication 기반으로 JWT 토큰 발급*/
        /*5-1. authentication으로 Access Token 발급*/
        /*5-2. authentication으로 Refresh Token 발급*/
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        /*5-2-1. Refresh Token을 Hashing 처리*/
        /*5-2-2. Refresh Token을 DB에 저장*/
        memberService.addRefreshToken(email, refreshToken);

        /*6. 클라이언트로 응답 메세지로 전송*/
        JwtTokenDTO jwtTokenDTO = JwtTokenDTO.builder()
                .result("fail")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(jwtTokenDTO);
    }
}
