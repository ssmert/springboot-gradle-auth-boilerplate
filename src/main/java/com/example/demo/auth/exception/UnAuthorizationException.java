package com.example.demo.auth.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseUnAuthorizationException;

/**
 * 인증 실패 예외
 */
public class UnAuthorizationException extends BaseUnAuthorizationException {
    public UnAuthorizationException(String message) {
        super(message);
    }

    public UnAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizationException(Errors errors) {
        super(errors);
    }
}
