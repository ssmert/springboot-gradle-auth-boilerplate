package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 인증 실패 예외이다.
 *
 * @author jonghyeon
 */
public class AuthException extends BaseRuntimeException {
    /**
     * 인증 실패 예외이다.
     *
     * @param code 오류코드
     */
    public AuthException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 인증 실패 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public AuthException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 인증 실패 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public AuthException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 인증 실패 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public AuthException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }

    /**
     * HTTP 응답상태(권한 없음: {@link HttpStatus#UNAUTHORIZED})를 반환한다.
     *
     * @return HTTP 응답상태
     */
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
