package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 사전조건 위반 예외이다.
 *
 * @author jonghyeon
 */
public class PreconditionFailedException extends BaseRuntimeException {
    /**
     * 사전조건 위반 예외이다.
     *
     * @param code 오류코드
     */
    public PreconditionFailedException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 사전조건 위반 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public PreconditionFailedException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 사전조건 위반 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public PreconditionFailedException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 사전조건 위반 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public PreconditionFailedException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }

    /**
     * HTTP 응답상태(사전조건 실패: {@link HttpStatus#PRECONDITION_FAILED})를 반환한다.
     *
     * @return HTTP 응답상태
     */
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
