package com.example.demo.core.exception;

import com.example.demo.core.domain.Errors;
import org.springframework.http.HttpStatus;

/**
 * 잘못된 인자 예외
 */
public class BaseIllegalArgumentException extends BaseRuntimeException {
    public BaseIllegalArgumentException(String message) {
        super(message);
    }

    public BaseIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseIllegalArgumentException(Errors errors) {
        super(errors, HttpStatus.BAD_REQUEST);
    }
}
