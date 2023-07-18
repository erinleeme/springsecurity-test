package com.example.demo.service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.exception.DuplicateCheckException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void createMember(MemberRequestDTO memberRequestDTO) {

        Integer isMember = memberMapper.getMember(memberRequestDTO.getEmail());
        if (isMember != null) { throw new DuplicateCheckException(ErrorCode.IS_EXIST_USER_BY_EMAIL); }

        System.out.println("여기까진 오니?1");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(memberRequestDTO.getMemberType())
                .build();

        System.out.println("여기까진 오니?2" + memberDAO);

        memberMapper.createMember(memberDAO);

        System.out.println("여기까진 오니?3");
    }

}
