package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 기본 최상위 예외이다.
 *
 * @author jonghyeon
 */
public class BaseRuntimeException extends RuntimeException {
    /**
     * 메세지 치환 아귀먼트 목록
     */
    private Object[] msgArgs;

    /**
     * 오류코드 초기화하는 생성자이다.
     *
     * @param code 오류코드
     */
    public BaseRuntimeException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public BaseRuntimeException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 오류코드를 초기화하는 생성자이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public BaseRuntimeException(String code, Object[] codeMessageArgs) {
        this(code, codeMessageArgs, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public BaseRuntimeException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, cause);
        this.msgArgs = codeMessageArgs;
    }

    /**
     * HTTP 응답상태(서버 내부 오류 : {@link HttpStatus#INTERNAL_SERVER_ERROR})를 반환한다.
     *
     * @return HTTP 응답상태
     */
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * 메세지 코드를 반환한다.
     *
     * @return 메세지 코드
     */
    public String getMsgCd() {
        return super.getMessage();
    }

    /**
     * 메세지 치환 아귀먼트 목록을 반환한다.
     *
     * @return 메세지 치환 아귀먼트 목록
     */
    public Object[] getMsgArgs() {
        return (null == msgArgs ? new Object[] {} : msgArgs);
    }
}
