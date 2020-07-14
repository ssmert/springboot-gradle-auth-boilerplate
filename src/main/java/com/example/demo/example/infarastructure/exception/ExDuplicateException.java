package com.example.demo.example.infarastructure.exception;

import com.example.demo.core.infrastructure.exception.DataDuplicateException;

/**
 * 예제 중복 예외이다.
 *
 * @author jonghyeon
 */
public class ExDuplicateException extends DataDuplicateException {
    /**
     * 예제 중복 예외이다.
     *
     * @param code 오류코드
     */
    public ExDuplicateException(String code) {
        this(code, new Object[] { }, null);
    }

    /**
     * 예제 중복 예외이다.
     *
     * @param code 오류코드
     * @param cause 원인예외
     */
    public ExDuplicateException(String code, Throwable cause) {
        this(code, new Object[] { }, cause);
    }

    /**
     * 예제 중복 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public ExDuplicateException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 예제 중복 예외이다.
     *
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public ExDuplicateException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
}
