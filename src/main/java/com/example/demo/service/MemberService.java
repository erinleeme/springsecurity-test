package com.example.demo.service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.exception.DuplicateCheckException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void createMember(MemberRequestDTO memberRequestDTO) {
        Integer isMember = memberMapper.getMember(memberRequestDTO.getEmail());
        if (isMember != null) { throw new DuplicateCheckException(ErrorCode.IS_EXIST_USER_BY_EMAIL); }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(memberRequestDTO.getMemberType())
                .build();

        log.info("MemberDAO : " + memberDAO);

        memberMapper.createMember(memberDAO);
    }
}
