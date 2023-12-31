package com.example.demo.controller;

import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        log.info("[MemberController] createMember 접속 완료!");
        memberService.createMember(memberRequestDTO);
        log.info("[MemberController] createMember 작업 완료!");
    }
}
