package com.example.demo.service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.Member;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*로그인 시 해당 계정의 유효성 검증을 해주는 Service 클래스*/
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    /*로그인한 회원이 기본 회원인지 확인하고 인증하는 메서드*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(email);
        /*회원 정보 유무 확인*/
        if(memberDAO == null) {
            log.info("유효하지 않은 이메일 주소");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        return new Member(memberDAO);
    }
}
