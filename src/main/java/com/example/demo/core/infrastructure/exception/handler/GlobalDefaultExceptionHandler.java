package com.example.demo.core.infrastructure.exception.handler;

import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 모든 예외에 대한 공통 핸들러이다.
 *
 * @author jonghyeon
 */
@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {
    //    @ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    //    @ResponseBody
    //    protected Response handleException(ConstraintViolationException exception) {
    //        return Response
    //                .builder()
    //                .header(Header
    //                        .builder()
    //                        .isSuccessful(false)
    //                        .resultCode(-400)
    //                        .resultMessage(getResultMessage(exception.getConstraintViolations().iterator())) // 오류 응답을 생성
    //                        .build())
    //                .build();
    //    }
    //
    //    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
    //        final StringBuilder resultMessageBuilder = new StringBuilder();
    //        while (violationIterator.hasNext() == true) {
    //            final ConstraintViolation<?> constraintViolation = violationIterator.next();
    //            resultMessageBuilder
    //                    .append("['")
    //                    .append(getPopertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
    //                    .append("' is '")
    //                    .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
    //                    .append("'. ")
    //                    .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
    //                    .append("]");
    //
    //            if (violationIterator.hasNext() == true) {
    //                resultMessageBuilder.append(", ");
    //            }
    //        }
    //
    //        return resultMessageBuilder.toString();
    //    }
    //
    //    protected String getPopertyName(String propertyPath) {
    //        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    //    }

    /**
     * 잘못된 인자 예외는 HTTP 응답상태({@link HttpStatus#BAD_REQUEST})로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param e 예외
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void argumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException e) throws IOException {
        // TODO 제대로 정리해서 처리할것
        response.sendError(HttpStatus.BAD_REQUEST.value());

        if (log.isErrorEnabled()) {
            log.error(String.format("Exception HTTP-STATUS [%d]", HttpStatus.BAD_REQUEST.value()), e);
        }
    }

    /**
     * 인증 예외는 HTTP 응답상태({@link HttpStatus#UNAUTHORIZED})로 처리한다.
     *
     * @param response HTTP 서블릿 응답객체
     * @param bce 권한 없음 예외
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
     * @param e 런타임 최상위 예외
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public void handleAnyApiRunTimeException(HttpServletResponse response, BaseRuntimeException e) throws IOException {
        // HTTP상태코드
        int httpStatusCode = (null == e.getResponseHttpStatus() ? HttpStatus.INTERNAL_SERVER_ERROR.value() : e.getResponseHttpStatus().value());
        response.sendError(httpStatusCode);

        if (log.isErrorEnabled()) {
            log.error(String.format("요청을 처리하던 중 오류가 발생했습니다. HTTP-STATUS [%d] : %s", httpStatusCode, e.getMessage()), e);
        }
    }

    /**
     * 미확인 예외는 HTTP 응답상태({@link HttpStatus#INTERNAL_SERVER_ERROR})로 처리한다.
     *
     * @param response HTTP 서블릿 응답데이터
     * @param t 최상위 예외
     */
    @ExceptionHandler(Throwable.class)
    public void handleAnyException(HttpServletResponse response, Throwable t) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다.");

        if (log.isErrorEnabled()) {
            log.error(String.format("요청을 처리하던 중 예상하지 못한 오류가 발생했습니다. HTTP-STATUS [%d]", HttpStatus.INTERNAL_SERVER_ERROR.value()), t);
        }
    }
}
