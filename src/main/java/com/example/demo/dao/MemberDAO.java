package com.example.demo.dao;

import com.example.demo.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;


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
