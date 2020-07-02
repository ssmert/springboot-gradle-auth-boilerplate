package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 잘못된 인자 예외이다.
 *
 * @author jonghyeon
 */
public class IllegalArgException extends BaseRuntimeException {
    /**
     * 잘못된 인자 예외이다.
     *
     * @param code 오류코드
     */
    public IllegalArgException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 잘못된 인자 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public IllegalArgException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 잘못된 인자 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public IllegalArgException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 잘못된 인자 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public IllegalArgException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }

    /**
     * HTTP 응답상태(잘못된 요청 : {@link HttpStatus#BAD_REQUEST})를 반환한다.
     *
     * @return HTTP 응답 상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
