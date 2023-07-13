package com.example.demo.controller;

import com.example.demo.dto.response.MemberResponseDTO;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession session;

    /*로그인 함수*/
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestParam String email, @RequestParam String password) {
        log.info("login 정보 : " + email);
        MemberResponseDTO memberResponseDTO = memberService.loginCheck(email, password);
        /*성공하면 success, 실패하면 fail 반환*/
        if(memberResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        } else {
            /*회원 정보가 있을 시 session에 해당 회원 정보를 저장*/
            session.setAttribute("memberId", memberResponseDTO.getMemberId());
            session.setAttribute("email", memberResponseDTO.getEmail());
            session.setAttribute("nickname", memberResponseDTO.getNickname());
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
    }
}
