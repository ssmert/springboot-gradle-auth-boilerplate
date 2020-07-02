package com.example.demo.auth.application;

import com.example.demo.auth.api.transferobject.AuthUserVo;
import com.example.demo.auth.util.CryptoUtil;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.infrastructure.exception.AuthException;
import com.example.demo.role.api.transferobject.RoleResponse;
import com.example.demo.role.application.RetrieveRoleService;
import com.example.demo.role.domain.Role;
import com.example.demo.user.application.RetrieveUserService;
import com.example.demo.user.domain.User;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * 사용자 인증 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {
    /**
     * 최고관리자 식별자
     */
    private static final String SUPER_ADMIN_ID = "sadmin";

    /**
     * 최고관리자 역할
     */
    private static final String SUPER_ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * 사용자를 조회하는 서비스
     */
    private final RetrieveUserService retrieveUserService;
    /**
     * 역할 조회 서비스
     */
    private final RetrieveRoleService retrieveRoleService;

    /**
     * 문자열 역할 목록기반의 {@link GrantedAuthority} 목록을 반환한다.
     *
     * @param userRoles 사용자역할목록
     * @return {@link GrantedAuthority} 목록
     */
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<Role> userRoles) {
        return Collections2.transform(userRoles, (Function<Role, GrantedAuthority>) userRole -> new SimpleGrantedAuthority(userRole.getRoleId()));
    }

    /**
     * 인증된 사용자정보를 반환한다.
     *
     * @param userId 사용자식별자
     * @return 인증된 사용자정보
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AuthUserVo authUserVo = null;

        // 최고관리자이면 최고관리자 정보를 반환한다.
        if (SUPER_ADMIN_ID.equals(userId)) {
            authUserVo = this.createSuperUser();
        } else {
            // 사용자 정보를 조회한다.
            User user = retrieveUserService.retrieveUserAuth(userId);

            if (null != user) {
                //사용자에 부여된 권한목록을 구한다.
                Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(user.getUserRoles());
                authUserVo = new AuthUserVo(user.getUserId(), user.getUserNm(), user.getUserPwd(), user.getUserUseYn(), true, true, true, true, authorities);
            }
        }

        return authUserVo;
    }

    /**
     * 최고관리자 인증정보를 생성하여 반환한다.
     *
     * @return 사용자 인증정보
     */
    private AuthUserVo createSuperUser() {
        try {
            // 최고관리자 역할을 구한다.
            RoleResponse roleRes = retrieveRoleService.retrieveRole(SUPER_ADMIN_ROLE);

            return new AuthUserVo(SUPER_ADMIN_ID, "최고관리자", CryptoUtil.getSHA512(SUPER_ADMIN_ID), YesOrNo.Yes, true, true, true, true,
                    Lists.newArrayList(new SimpleGrantedAuthority(roleRes.getRoleId())));
        } catch (Exception e) {
            throw new AuthException("로그인 처리중 알수없는 오류가 발생했습니다.\n관리자에게 문의하십시오", e);
        }
    }
}
