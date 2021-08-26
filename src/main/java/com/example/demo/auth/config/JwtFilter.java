package com.example.demo.auth.config;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.example.demo.auth.api.dto.AuthUser;
import com.example.demo.auth.api.dto.JwtVo;
import com.example.demo.auth.application.AuthUserDetailsService;
import com.example.demo.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final List<String> ALLOW_URLS = Lists.newArrayList("/swagger", "/v3/api-docs", "/server/", "/auth");

    private final JwtUtil jwtUtil;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 아래 경로는 이 필터가 적용되지 않는다.
        if (Iterables.any(ALLOW_URLS, request.getServletPath()::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 추출
        String token = resolveToken(request);

        if (null != token) {
            // 토큰 검증 및 추출
            JwtVo jwtVo = jwtUtil.validateToken(token);
            if (null != jwtVo) {
                // 사용자 조회
                AuthUser authUser = (AuthUser)authUserDetailsService.loadUserByUsername(jwtVo.getLoginId());

                // 요청정보를 컨텍스트에 저장
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser, null, jwtVo.getGrantedAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 토큰을 헤더에서 추출한다.
     *
     * @param request 요청 데이터
     *
     * @return jwt
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

}
