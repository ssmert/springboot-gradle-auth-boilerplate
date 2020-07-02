package com.example.demo.auth.configuration;

import com.example.demo.auth.api.transferobject.AuthUserVo;
import com.example.demo.auth.application.AuthService;
import com.example.demo.core.infrastructure.constant.RequiredHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증요청에 해당하는 URL을 감지하여 헤더에서 인증토큰을 추출하여 인증처리를 담당한다.
 *
 * @author jonghyeon
 */
@Slf4j
public class AuthTokenFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 토큰
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 인증 서비스
     */
    @Autowired
    private AuthService authService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 헤더로부터 인증토큰(JWT)을 구한다.
        String authToken = httpRequest.getHeader(RequiredHeader.AuthToken.getCode());

        // 인증받은 토큰이 존재할 경우 토큰 만료기간 이전이면 정상 인증처리한다.
        if (null != jwtTokenUtil.getUserIdFromToken(authToken)) {
            AuthUserVo currentUser = (AuthUserVo) authService.loadUserByUsername(jwtTokenUtil.getUserIdFromToken(authToken));

            //사용자 인증 토큰이 유효한 것인지 확인한다.
            if (null != currentUser && jwtTokenUtil.validateToken(authToken, currentUser)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(currentUser, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(jwtTokenUtil.getAuthoritiesFromToken(authToken)));

                // 요청정보를 상세정보로 저장한다.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(httpRequest, httpResponse);
    }
}
