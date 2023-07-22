package com.example.demo.controller;

import com.example.demo.dto.request.MemberLoginDTO;
import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.dto.response.JwtResponseDTO;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        memberService.createMember(memberRequestDTO);
    }

    @PostMapping("/jwtlogin")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {
        JwtResponseDTO jwtResponseDTO = memberService.login(memberLoginDTO);
        log.info("[MemberController] LOGIN 결과 : " + jwtResponseDTO.getResult());
        return ResponseEntity.status(OK).body(jwtResponseDTO);
    }
}
