package com.example.demo.auth.application;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.example.demo.auth.api.dto.AuthUser;
import com.example.demo.role.domain.Role;
import com.example.demo.user.application.UserService;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 인증 사용자 상세 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserService userService;

    /**
     * 문자열 역할 목록기반의 {@link GrantedAuthority} 목록을 반환한다.
     *
     * @param userRoles 사용자역할목록
     *
     * @return {@link GrantedAuthority} 목록
     */
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(Collection<Role> userRoles) {
        return Collections2.transform(userRoles, (Function<Role, GrantedAuthority>)userRole -> new SimpleGrantedAuthority(userRole.getRoleCode()));
    }

    /**
     * 인증된 사용자정보를 반환한다.
     *
     * @param loginId 로그인 아이디
     *
     * @return 인증된 사용자
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userService.findByLoginIdForAuth(loginId);
        return new AuthUser(user.getUserId(), user.getLoginId(), user.getUserNm(), user.getUserPw(), user.getUseYn(),
                getGrantedAuthorities(user.getUserRoles()));
    }
}
