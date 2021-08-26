package com.example.demo.core.exception;

import com.example.demo.core.domain.Errors;
import org.springframework.http.HttpStatus;

/**
 * 사전조건 위반 예외
 */
public class BasePreconditionFailedException extends BaseRuntimeException {
    public BasePreconditionFailedException(String message) {
        super(message);
    }

    public BasePreconditionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasePreconditionFailedException(Errors errors) {
        super(errors, HttpStatus.PRECONDITION_FAILED);
    }
}
