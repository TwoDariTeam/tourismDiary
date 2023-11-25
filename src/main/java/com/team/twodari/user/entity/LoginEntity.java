package com.team.twodari.user.entity;

import com.team.twodari.common.jwt.LongRoleAuthority;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginEntity implements UserDetails {
    private String email;
    private String nickname;
    private String password;
    private Long roleSeq;

    @Override //권한 체크 기존에 만들었던 권한 하나만 부여하는 것 활용
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new LongRoleAuthority(roleSeq));
    }


    @Override //이메일값을 리턴
    public String getUsername() {
        return email;
    }

    @Override //계정 만료 - 자동 로그아웃과 다르게 완전 만료시켜 로그인 불가. - 임시적으로 항상  true
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //여러번 로그인 실패시 로그인 잠시 막는 기능 구현 false 비로그인 - DB 연동 필수
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //3개월마다 비밀번호 변경 권고 기능 구현 false 두면 로그인 비허용
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //사용자 승인 요구
    public boolean isEnabled() {
        return true;
    }
}
