package com.example.demo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그 인터셉터
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    /**
     * 거래 시작 시 핸들러이다.
     *
     * @param request 요청 데이터
     * @param response 응답 데이터
     * @param handler 핸들러
     *
     * @return 항상 참
     *
     * @exception Exception 예외
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getServletPath().startsWith("/error")) {
            log.info("======================================          START         ======================================");
            log.info("{} {} ", request.getServletPath(), request.getMethod());
        }
        return true;
    }

    /**
     * 거래 종료 시 핸들러이다.
     *
     * @param request 요청 데이터
     * @param response 응답 데이터
     * @param handler 핸들러
     * @param exception 예외
     *
     * @exception Exception 예외
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        if (!request.getServletPath().startsWith("/error")) {
            log.info("{} {} {}", request.getServletPath(), request.getMethod(), HttpStatus.resolve(response.getStatus()));
            log.info("======================================          END         ======================================");
        }
    }
}
