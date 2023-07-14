package com.example.demo.controller;

import com.example.demo.dto.JwtTokenDTO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.service.JwtService;
import com.example.demo.service.MemberService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    /*로그인 함수*/
    @ResponseBody
    @Validated
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginCheck(@RequestParam @Email String email, @RequestParam String password) {
        /*1. AuthenticationFilter를 통해 ID와 PW 유효성 겁사 진행*/
        /*2. UserPasswordAuthenticationToken 발급*/
        /*3. 발급된 Token으로 AuthenticationManger로 보내져 사용자 유효성 검사 진행*/
        /*3-1. Email로 사용자 조회*/
        /*3-2. 아이디랑 비밀번호 일치하는지 확인*/
        /*4. 인증에 성공하면 UserPasswordAuthenticationToken기반으로 JWT 토큰 발급*/
        /*4-1. MemberId, MemberType으로 Access Token 발급*/
        /*4-2. MemberId, MemberType으로 Refresh Token 발급*/
        /*4-2-1. Refresh Token을 Hashing 처리*/
        /*4-2-2. Refresh Token을 DB에 저장*/
        MemberResponseDTO memberResponseDTO = memberService.loginCheck(email, password);

        /*5. Token을 저장하여 보내기 */


        /*성공하면 success, 실패하면 fail 반환*/


        return ResponseEntity.status(HttpStatus.OK).body(JwtTokenDTO);
    }
}
