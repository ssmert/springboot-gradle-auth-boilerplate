package com.example.demo.auth.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseBadRequestException;

/**
 * 토큰 만료 예외
 */
public class TokenExpiredException extends BaseBadRequestException {
    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpiredException(Errors errors) {
        super(errors);
    }
}