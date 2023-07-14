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
        /*1. 유효성 검사*/
        /*2. Email로 사용자 조회*/
        /*3. 아이디랑 비밀번호 일치하는지 확인*/
        MemberResponseDTO memberResponseDTO = memberService.loginCheck(email, password);

        if(memberResponseDTO == null) {
            JwtTokenDTO jwtTokenDTO = JwtTokenDTO.builder()
                    .result("fail")
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jwtTokenDTO); /*아이디와 비밀번호 실패시 인증실패 상태 반환*/
        }

        /*4. 토큰 발급*/
        /*4-1. MemberId, MemberType으로 Access Token 발급*/
        String accessToken = jwtService.generateAccessToken(memberResponseDTO.getMemberId(), memberResponseDTO.getMemberType());

        /*4-2. MemberId, MemberType으로 Refresh Token 발급*/
        /*4-2-1. Refresh Token을 Hashing 처리*/
        /*4-2-2. Refresh Token을 DB에 저장*/
        String refreshToken = jwtService.generateRefreshToken(memberResponseDTO.getMemberId(), memberResponseDTO.getMemberType());

        /*5. Token을 저장하여 보내기 */
        /*성공하면 success, 실패하면 fail 반환*/
        JwtTokenDTO jwtTokenDTO = JwtTokenDTO.builder()
                .result("success")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(jwtTokenDTO);
    }
}
