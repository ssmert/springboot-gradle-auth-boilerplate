package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 데이터 중복 예외이다.
 *
 * @author jonghyeon
 */
public class DataDuplicateException extends BaseRuntimeException {

    /**
     * 데이터 중복 예외이다.
     *
     * @param code 오류코드
     */
    public DataDuplicateException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 데이터 중복 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public DataDuplicateException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 데이터 중복 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public DataDuplicateException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 데이터 중복 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public DataDuplicateException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }

    /**
     * HTTP 응답상태(중복 됨 : {@link HttpStatus#CONFLICT})를 반환한다.
     *
     * @return HTTP 응답 상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
