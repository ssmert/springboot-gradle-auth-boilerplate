package com.example.demo.auth.api.transferobject;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 사용자 인증정보 객체이다.
 *
 * @author jonghyeon
 */
public class AuthUserVo extends User {
    /**
     * 사용자명
     */
    @Getter
    private String userNm;

    /**
     * 사용여부
     */
    @Getter
    private YesOrNo useYn;

    /**
     * 사용자 인증정보를 초기화하는 생성자이다.
     *
     * @param userId                사용자 식별자
     * @param userNm                사용자 명
     * @param password              사용자 비밀번호
     * @param useYn                 사용여부
     * @param enabled               사용자 활성여부
     * @param accountNonExpired     사용자 만료안됨 여부
     * @param credentialsNonExpired 자격증명 만료안됨 여부
     * @param accountNonLocked      사용자 잠금안됨 여부
     * @param authorities           사용자 권한(역할) 목록
     */
    public AuthUserVo(String userId, String userNm, String password, YesOrNo useYn, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.userNm = userNm;
        this.useYn = useYn;
    }

    /**
     * 사용자 식별자를 반환한다.
     *
     * @return 사용자 식별자
     */
    public String getUserId() {
        return getUsername();
    }
}
