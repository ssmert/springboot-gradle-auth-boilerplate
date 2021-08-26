package com.example.demo.core.dto;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseRuntimeException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final int code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseRuntimeException e) {
        return ResponseEntity
                .status(e.getErrorStatus())
                .body(ErrorResponse.builder()
                        .status(e.getErrorStatus())
                        .error(HttpStatus.valueOf(e.getErrorStatus()).name())
                        .code(e.getErrorCode())
                        .message(e.getErrorMessage())
                        .build());
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus httpStatus, Errors errors) {
        return ResponseEntity
                .status(httpStatus.value())
                .body(ErrorResponse.builder()
                        .status(httpStatus.value())
                        .error(httpStatus.name())
                        .code(errors.getCode())
                        .message(errors.getMessage())
                        .build());
    }
}