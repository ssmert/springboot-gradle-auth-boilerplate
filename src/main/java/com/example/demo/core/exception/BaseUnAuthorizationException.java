package com.example.demo.core.exception;

import com.example.demo.core.domain.Errors;
import org.springframework.http.HttpStatus;

/**
 * 인증 실패 예외
 */
public class BaseUnAuthorizationException extends BaseRuntimeException {
    public BaseUnAuthorizationException(String message) {
        super(message);
    }

    public BaseUnAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseUnAuthorizationException(Errors errors) {
        super(errors, HttpStatus.UNAUTHORIZED);
    }
}
