package com.example.demo.controller;

import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String loginCheck(@RequestParam String email, @RequestParam String password) {
        log.info("login 정보 : " + email);
        return memberService.loginCheck(email, password); /*성공하면 success, 실패하면 fail 반환*/
    }
}
