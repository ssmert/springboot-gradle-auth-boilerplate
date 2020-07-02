package com.example.demo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 데이터 없음 예외이다.
 *
 * @author jonghyeon
 */
public class DataNotFoundException extends BaseRuntimeException {
    /**
     * 데이터 없음 예외이다.
     *
     * @param code 오류코드
     */
    public DataNotFoundException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 데이터 없음 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public DataNotFoundException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 데이터 없음 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public DataNotFoundException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 데이터 없음 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public DataNotFoundException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }

    /**
     * HTTP 응답상태(존재하지 않음 : {@link HttpStatus#NOT_FOUND})를 반환한다.
     *
     * @return HTTP 응답상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
