package com.example.demo.controller;

import com.example.demo.dto.request.MemberLoginDTO;
import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private AuthenticationManager authenticationManager;
    private SecurityContextHolderStrategy securityContextHolderStrategy;

    /*회원가입*/
    @PostMapping("/members")
    public void createMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        log.info("[MemberController] createMember 접속 완료!");
        memberService.createMember(memberRequestDTO);
        log.info("[MemberController] createMember 작업 완료!");
    }

    /*로그인*/
    @PostMapping("/login")
    public void login(@RequestBody @Valid MemberLoginDTO memberLoginDTO, HttpServletRequest request, HttpServletResponse response) {

        log.info("[MemberController] login method 접속");

        /*1. 사용자가 보낸 username과 password로 usertoken 생성*/
        UsernamePasswordAuthenticationToken userToken = UsernamePasswordAuthenticationToken.unauthenticated(
                memberLoginDTO.getEmail(), memberLoginDTO.getPassword());

        /*2. usertoken으로 AuthenticationManager로 보내 인증 과정 거친 Authentication 객체 반환*/
        Authentication authentication = authenticationManager.authenticate(userToken);

        /*3. 인증 과정에 통과한 Authentication 객체를 SecurityContextHolder에 저장*/
        SecurityContext securityContext = securityContextHolderStrategy.createEmptyContext();
        securityContext.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(securityContext);
        securityContextRepository.saveContext(securityContext, request, response);
    }
}
