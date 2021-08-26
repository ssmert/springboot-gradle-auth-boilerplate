package com.example.demo.auth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 토큰 vo
 */
@Getter
@AllArgsConstructor
public class JwtVo {
    private long userId;
    private String loginId;
    private boolean isExpired;
    private Collection<GrantedAuthority> grantedAuthorities;
}
