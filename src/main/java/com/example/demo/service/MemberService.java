package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberResponseDTO;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*Member 테이블에 refresh_column에 값 저장하는 함수*/
    public void addRefreshToken(String email, String refreshToken) {
        /*Hashing 처리*/
        String hashRefreshToken = bCryptPasswordEncoder.encode(refreshToken);
        memberMapper.addRefreshToken(email, hashRefreshToken);
    }
}
