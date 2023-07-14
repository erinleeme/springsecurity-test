package com.example.demo.dto;

import com.example.demo.dao.MemberDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*사용자의 정보를 저장하는 인터페이스 구현*/
public class MemberDetail implements UserDetails {

    private final MemberDAO memberDAO;

    public MemberDetail(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    /*사용자에게 부여된 권한을 지정한 컬렉션 반환*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /*계정 정보*/
    @Override
    public String getPassword() {
        return memberDAO.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDAO.getEmail();
    }

    /*계정 만료 유무*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*계정 잠금 상태 유무*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*비밀번호 만료 유무*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*계정 활성화 유무*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
