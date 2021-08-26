package com.example.demo.auth.config;

import com.example.demo.auth.api.dto.AuthUser;
import com.example.demo.auth.application.AuthUserDetailsService;
import com.example.demo.auth.exception.UnAuthorizationException;
import com.example.demo.core.domain.Errors;
import com.example.demo.core.domain.YesOrNo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 사용자 인증 제공자이다.
 */
@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {
    private final AuthUserDetailsService authUserDetailsService;
    private final AuthPasswordEncoder authPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = (String)authentication.getCredentials();
        AuthUser user = (AuthUser)authUserDetailsService.loadUserByUsername(id);

        // 비밀번호가 다를 때
        if (!authPasswordEncoder.matches(password, user.getPassword())) {
            throw new UnAuthorizationException(Errors.LOGIN_FAILED_ID_OR_PW);
        }

        // 정지된 사용자이면 예외발생
        if (YesOrNo.No == user.getUseYn()) {
            throw new UnAuthorizationException(Errors.SUSPEND_USER);
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
