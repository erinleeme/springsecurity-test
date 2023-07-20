package com.example.demo.dao;

import com.example.demo.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDAO {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private MemberType memberType;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
