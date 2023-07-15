package com.example.demo.service;

import com.example.demo.dto.MemberDetail;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDetail member = memberMapper.selectMemberByEmail(email);
        /*회원 정보 유무 확인*/
        if(member == null) {
            log.info("유효하지 않은 이메일 주소");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
        return member;
    }
}
