package com.example.demo.mapper;

import com.example.demo.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDAO selectMemberByEmail(String email);

    void addRefreshToken(@Param("memberId") Long memberId, @Param("hashRefreshToken") String hashRefreshtoken);
}
