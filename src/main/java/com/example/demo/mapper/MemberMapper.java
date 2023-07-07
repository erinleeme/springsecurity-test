package com.example.demo.mapper;

import com.example.demo.dao.MemberDAO;

public interface MemberMapper {
    MemberDAO selectMemberByEmail(String email);
}
