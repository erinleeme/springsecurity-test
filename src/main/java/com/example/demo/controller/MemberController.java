package com.example.demo.controller;

import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        System.out.println("여기까진 오니?createMember");
        memberService.createMember(memberRequestDTO);
        System.out.println("createMember 끝");
    }
}
