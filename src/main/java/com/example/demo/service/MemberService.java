package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
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
    private final HttpSession session;

    @Transactional
    public String loginCheck(String email, String password) {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(email);

        /*회원 정보 유무 확인*/
        if(memberDAO == null) {
            return "fail";
        }

        /*비밀번호 일치 확인*/
        if(!bCryptPasswordEncoder.matches(password, memberDAO.getPassword())) {
            log.info("비밀번호 불일치");
            return "fail";
        }

        /*회원 정보가 있을 시 session에 해당 회원 정보를 저장*/
        session.setAttribute("memberId", memberDAO.getMemberId());
        session.setAttribute("email", memberDAO.getEmail());
        session.setAttribute("nickname", memberDAO.getNickname());

        return "success";
    }
}
