package com.example.demo.auth.api.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * 인증 응답 데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements Serializable {
    /**
     * 토큰(JWT)
     */
    private String token;

    /**
     * 사용자식별자
     */
    private String userId;

    /**
     * 사용자 명
     */
    private String userNm;

    /**
     * 사용자역할
     */
    private Collection<GrantedAuthority> userRoles;


}
