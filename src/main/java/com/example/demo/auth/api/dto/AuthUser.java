package com.example.demo.auth.api.dto;

import com.example.demo.core.domain.YesOrNo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 인증 사용자
 */
@Getter
public class AuthUser extends User {
    private Long userId;
    private YesOrNo useYn;

    /**
     * 사용자 인증정보를 초기화하는 생성자이다.
     *
     * @param userId 사용자 식별자
     * @param userNm 사용자명
     * @param password 사용자 비밀번호
     * @param useYn 사용여부
     * @param authorities 권한(역할) 목록
     */
    public AuthUser(Long userId, String loginId, String userNm, String password, YesOrNo useYn, Collection<? extends GrantedAuthority> authorities) {
        super(loginId, password, true, true, true, true, authorities);
        this.userId = userId;
        this.useYn = useYn;
    }
}
