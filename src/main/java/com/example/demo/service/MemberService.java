package com.example.demo.service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDAO member = memberMapper.selectMemberByEmail(email);
        /*회원 정보 유무 확인*/
        if(member == null) {
            log.info("유효하지 않은 이메일 주소");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
        return member;
    }
}
