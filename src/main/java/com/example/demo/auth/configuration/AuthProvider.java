package com.example.demo.auth.configuration;

import com.example.demo.auth.api.transferobject.AuthUserVo;
import com.example.demo.auth.application.AuthService;
import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.infrastructure.exception.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 사용자 인증 제공자이다.
 *
 * @author jonghyeon
 */
@Component
@AllArgsConstructor
public class AuthProvider implements AuthenticationProvider {
    /**
     * 인증 서비스
     */
    private final AuthService authService;

    /**
     * 비밀번호 인코더
     */
    private final AuthPasswordEncoder authPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = (String) authentication.getCredentials();

        AuthUserVo user = (AuthUserVo) authService.loadUserByUsername(id);
        if (null == user) {
            throw new AuthException(Errors.AuthErrCd.AUTHS001.getCode());
        }

        // 비밀번호가 다르면 예외발생
        if (!authPasswordEncoder.matches(password, user.getPassword())) {
            throw new AuthException(Errors.AuthErrCd.AUTHS002.getCode());
        }

        // 정지된 사용자이면 예외발생
        if (YesOrNo.No == user.getUseYn()) {
            throw new AuthException(Errors.AuthErrCd.AUTHS003.getCode());
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
