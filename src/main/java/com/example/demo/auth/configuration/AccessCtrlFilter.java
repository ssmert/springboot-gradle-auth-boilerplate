package com.example.demo.auth.configuration;


import com.example.demo.core.infrastructure.constant.EnvCd;
import com.example.demo.core.infrastructure.constant.RequiredHeader;
import com.example.demo.core.infrastructure.exception.AuthException;
import com.example.demo.core.util.CheckUtil;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * 환경코드 필터이다.
 *
 * @author jonghyeon
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@AllArgsConstructor
public class AccessCtrlFilter extends OncePerRequestFilter {
    /**
     * 허용된 URI 목록
     */
    private final Set<String> allowedUris = ImmutableSet
            .copyOf(Arrays.asList("/css", "/js", "/resources", "/webjars", "/developers", "/configuration", "/v2/api-docs", "/swagger-resources", "/swagger-ui.html", "/csrf"));
    /**
     * 스프링 환경 제공자
     */
    private final Environment environment;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 현재 요청 URI를 구하여 허용여부를 구한다.
        final String reqUri = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        try {
            if (log.isInfoEnabled()) {
                log.info(String.format("Request URI: (%s) ----- [ AccessCtrlFilter - START ]", reqUri));
            }

            checkAccessAllowed(reqUri, request, response);
            chain.doFilter(request, response);
        } finally {
            if (log.isInfoEnabled()) {
                log.info(String.format("Request URI: (%s) ----- [ AccessCtrlFilter - END ]", reqUri));
            }
        }
    }

    /**
     * 유효한 환경코드가 아니면 예외를 발생시킨다.
     *
     * @param reqUri   요청 URI
     * @param request  요청객체
     * @param response 응답객체
     */
    private void checkAccessAllowed(final String reqUri, HttpServletRequest request, HttpServletResponse response) throws AuthException {
        // 환경코드 URI 접근허용 여부를 구한다.
        boolean isAllowedUri = Iterables.any(allowedUris, reqUri::startsWith);

        // 환경코드에 상관없이 접근이 허용된 URI가 아니라면 환경코드를 검사한다.
        if (!isAllowedUri) {
            // 서버 환경코드를 구한다.
            final EnvCd curEnvCd = EnvCd.codeOf(CheckUtil.nvl(environment.getActiveProfiles()[0], null));
            // 요청 환경코드를 구한다.
            final EnvCd reqEnvCd = EnvCd.codeOf(CheckUtil.nvl(request.getHeader(RequiredHeader.EnvType.getCode()), null));

            log.info(String.format("S-Env : (%s) / C-Env : (%s) ----- [ AccessCtrlFilter ]", curEnvCd, reqEnvCd));

            // 요청 환경코드가 없거나 유효한 환경코드가 아니면...
            if (null == reqEnvCd) {
                // 서비스에 허가된 접근이 아님을 알리는 메세지를 작성해서 응답한다.
                throw new AuthException("접근이 허가된 환경이 아닙니다.");
            } else {
                // 서버 환경코드와 요청 환경코드가 다르면 예외를 발생시킨다.
                if (!curEnvCd.getCode().equals(reqEnvCd.getCode())) {
                    throw new AuthException(String.format("환경코드[%s]와 상이한 환경코드 [%s]로의 요청이므로 접근을 차단합니다.", curEnvCd.getTitle(), reqEnvCd.getTitle()));
                }
            }
        }
    }
}
