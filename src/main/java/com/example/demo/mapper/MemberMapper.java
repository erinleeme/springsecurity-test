package com.example.demo.mapper;

import com.example.demo.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDAO selectMemberByEmail(String email);

    void addRefreshToken(@Param("email") String email, @Param("hashRefreshToken") String hashRefreshToken);
}
