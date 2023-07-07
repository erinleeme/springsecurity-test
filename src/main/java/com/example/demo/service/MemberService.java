package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void login(String email, String password) {

        /*들어온 email로 Member 데이터 조회*/
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(email);

        if(memberDAO==null) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        } else{
            /*있는 경우 요청메세지를 통해 들어온 password와 일치한지 확인*/


        }





    }



}
