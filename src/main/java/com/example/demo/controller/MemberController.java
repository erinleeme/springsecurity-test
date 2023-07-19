package com.example.demo.controller;

import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    /*회원가입*/
    @PostMapping("/members")
    public void createMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        log.info("[MemberController] createMember 접속 완료!");
        memberService.createMember(memberRequestDTO);
        log.info("[MemberController] createMember 작업 완료!");
    }

    /*로그인 session check*/
    @GetMapping("/session")
    public void sessionCheck() {
        if (httpSession != null) {
            SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                String username = authentication.getName();
                log.info("인증된 사용자 이름: " + username);
            }
        }
    }
}
