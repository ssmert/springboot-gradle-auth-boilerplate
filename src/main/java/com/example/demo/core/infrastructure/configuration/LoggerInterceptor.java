package com.example.demo.core.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http 거래에 대한 로그 인터셉터이다.
 *
 * @author jonghyeon
 */
@Slf4j
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    /**
     * 거래 시작 시 핸들러이다.
     *
     * @param request 요청 데이터
     * @param response 응답 데이터
     * @param handler 핸들러
     *
     * @return 항상 참
     * @throws Exception 예외
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("======================================          START         ======================================");
        log.info(String.format("%s %s", request.getRequestURI(), request.getMethod()));
        return super.preHandle(request, response, handler);
    }

    /**
     * 거래 종료 시 핸들러이다.
     *
     * @param request 요청 데이터
     * @param response 응답 데이터
     * @param handler 핸들러
     * @param exception 예외
     *
     * @throws Exception 예외
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        log.info(String.format("%s %s", request.getRequestURI(), request.getMethod()));
        log.info("======================================          END         ======================================");
    }
}
