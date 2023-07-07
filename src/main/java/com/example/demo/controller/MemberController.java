package com.example.demo.controller;

import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam String email, String password) {
        memberService.login(email, password);
        return "login success!";
    }
}
