package com.example.demo.core.infrastructure.exception.handler;

import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 모든 예외에 대한 공통 핸들러이다.
 *
 * @author jonghyeon
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalDefaultExceptionHandler {
    /**
     * 인증 예외는 HTTP 응답상태({@link HttpStatus#UNAUTHORIZED})로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param bce      권한없음 예외
     */
    @ExceptionHandler(BadCredentialsException.class)
    public void handleBadCredentialsException(HttpServletResponse response, BadCredentialsException bce) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());

        if (log.isErrorEnabled()) {
            log.error(String.format("Authentication Exception HTTP-STATUS [%d]", HttpStatus.UNAUTHORIZED.value()), bce);
        }
    }

    /**
     * 런타임 예외는 {@link Errors}에 정의된 예외 코드와 메시지로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param bdrte    런타임 최상위 예외
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public void handleAnyApiRunTimeException(HttpServletResponse response, BaseRuntimeException bdrte) throws IOException {
        // HTTP상태코드
        int httpStatusCode = (null == bdrte.getResponseHttpStatus() ? HttpStatus.INTERNAL_SERVER_ERROR.value() : bdrte.getResponseHttpStatus().value());

        response.sendError(httpStatusCode);

        if (log.isErrorEnabled()) {
            log.error(String.format("요청을 처리하던 중 오류가 발생했습니다. HTTP-STATUS [%d] : %s", httpStatusCode, bdrte.getMessage()), bdrte);
        }
    }

    /**
     * 미확인 예외는 HTTP 응답상태({@link HttpStatus#INTERNAL_SERVER_ERROR})로 처리한다.
     *
     * @param response HTTP 서블릿 응답데이터
     * @param t        최상위 예외
     */
    @ExceptionHandler(Throwable.class)
    public void handleAnyException(HttpServletResponse response, Throwable t) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다.");

        if (log.isErrorEnabled()) {
            log.error(String.format("요청을 처리하던 중 예상하지 못한 오류가 발생했습니다. HTTP-STATUS [%d]", HttpStatus.INTERNAL_SERVER_ERROR.value()), t);
        }
    }
}
