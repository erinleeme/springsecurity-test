package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.response.MemberResponseDTO;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
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

    @Transactional
    public MemberResponseDTO loginCheck(String email, String password) {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(email);
        /*회원 정보 유무 확인*/
        if(memberDAO == null) {
            log.info("유효하지 않은 이메일 주소");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
        /*비밀번호 일치 확인*/
        if(!bCryptPasswordEncoder.matches(password, memberDAO.getPassword())) {
            log.info("비밀번호 불일치");
            return null;
        }

        MemberResponseDTO memberResponseDTO = MemberResponseDTO.builder()
                .memberId(memberDAO.getMemberId())
                .email(memberDAO.getEmail())
                .nickname(memberDAO.getEmail())
                .build();

        return memberResponseDTO;
    }
}
