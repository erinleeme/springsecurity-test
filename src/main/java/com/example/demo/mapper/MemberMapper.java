package com.example.demo.mapper;

import com.example.demo.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDAO selectMemberByEmail(String email);

    int getMember(String email);

    int createMember(MemberDAO memberDAO);
}
