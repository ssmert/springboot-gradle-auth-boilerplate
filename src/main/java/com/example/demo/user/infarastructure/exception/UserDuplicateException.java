package com.example.demo.user.infarastructure.exception;

import com.example.demo.core.infrastructure.exception.DataDuplicateException;

/**
 * 사용자 중복 예외이다.
 *
 * @author jonghyeon
 */
public class UserDuplicateException extends DataDuplicateException {
    /**
     * 사용자 중복 예외이다.
     *
     * @param code 오류코드
     */
    public UserDuplicateException(String code) {
        this(code, new Object[]{}, null);
    }

    /**
     * 사용자 중복 예외이다.
     *
     * @param code  오류코드
     * @param cause 원인예외
     */
    public UserDuplicateException(String code, Throwable cause) {
        this(code, new Object[]{}, cause);
    }

    /**
     * 사용자 중복 예외이다.
     *
     * @param code            오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public UserDuplicateException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 사용자 중복 예외이다.
     *
     * @param code            오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause           원인예외
     */
    public UserDuplicateException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
}
