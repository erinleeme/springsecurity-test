package com.example.demo.mapper;

import com.example.demo.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDAO selectMemberByEmail(String email);
    void createMember(MemberDAO memberDAO);
}
