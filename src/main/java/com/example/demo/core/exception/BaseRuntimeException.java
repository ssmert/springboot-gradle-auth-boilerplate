package com.example.demo.core.exception;

import com.example.demo.core.domain.Errors;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 기본 최상위 예외
 */
@Getter
public class BaseRuntimeException extends RuntimeException {
    private int errorCode = Errors.INTERNAL_SERVER_ERROR.getCode();
    private String errorMessage = Errors.INTERNAL_SERVER_ERROR.getMessage();
    private int errorStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Errors errors, HttpStatus httpStatus) {
        super(errors.getMessage());
        this.errorCode = errors.getCode();
        this.errorMessage = errors.getMessage();
        this.errorStatus = httpStatus.value();
    }
}
