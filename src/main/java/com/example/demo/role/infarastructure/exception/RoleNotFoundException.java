package com.example.demo.role.infarastructure.exception;


import com.example.demo.core.infrastructure.exception.DataNotFoundException;

/**
 * 역할 데이터 없음 예외이다.
 *
 * @author jonghyeon
 */
public class RoleNotFoundException extends DataNotFoundException {
    /**
     * 역할 데이터 없음 예외이다.
     *
     * @param code 오류코드
     */
    public RoleNotFoundException(String code) {
        this(code, new Object[]{}, null);
    }

    /**
     * 역할 데이터 없음 예외이다.
     *
     * @param code  오류코드
     * @param cause 원인예외
     */
    public RoleNotFoundException(String code, Throwable cause) {
        this(code, new Object[]{}, cause);
    }

    /**
     * 역할 데이터 없음 예외이다.
     *
     * @param code            오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public RoleNotFoundException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }

    /**
     * 역할 데이터 없음 예외이다.
     *
     * @param code            오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause           원인예외
     */
    public RoleNotFoundException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
}
