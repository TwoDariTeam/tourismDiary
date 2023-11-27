package com.team.twodari.common.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

//권한을 여러개 갖고있지 않고 한개만 갖고 있을 떄 가정하고 만든 클래스

@AllArgsConstructor
public class LongRoleAuthority implements GrantedAuthority {
    private final Long authorityValue;

    @Override
    public String getAuthority() {
        return authorityValue.toString();
    }
}
