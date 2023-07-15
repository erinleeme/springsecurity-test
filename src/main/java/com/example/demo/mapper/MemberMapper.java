package com.example.demo.mapper;

import com.example.demo.dto.MemberDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDetail selectMemberByEmail(String email);
}
