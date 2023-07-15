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
public class MemberDAO implements UserDetails {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private MemberType memberType;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    /*사용자에게 부여된 권한을 지정한 컬렉션 반환*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /*계정 정보*/
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    /*계정 만료 유무*/
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /*계정 잠금 상태 유무*/
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /*비밀번호 만료 유무*/
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /*계정 활성화 유무*/
    @Override
    public boolean isEnabled() {
        return false;
    }
}
