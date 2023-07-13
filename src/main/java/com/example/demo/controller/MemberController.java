package com.example.demo.controller;

import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /*로그인 함수*/
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestParam String email, @RequestParam String password) {
        /*1. 유효성 검사*/
        /*2. Email로 사용자 조회*/
        /*3. 아이디랑 비밀번호 일치하는지 확인*/
        MemberResponseDTO memberResponseDTO = memberService.loginCheck(email, password);

        /*4. 토큰 발급*/
        /*4-1. Email로 Access Token 발급*/
        /*4-2. Email로 Refresh Token 발급*/
        /*4-2-1. Refresh Token을 Hashing 처리*/
        /*4-2-2. Refresh Token을 DB에 저장*/

        /*5. 쿠키에 Access Token을 저장하여 보내기 */




        /*성공하면 success, 실패하면 fail 반환*/
            return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
