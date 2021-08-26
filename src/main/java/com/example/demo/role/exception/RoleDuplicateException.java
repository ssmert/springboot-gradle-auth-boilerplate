package com.example.demo.role.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseDuplicateException;

public class RoleDuplicateException extends BaseDuplicateException {
    public RoleDuplicateException(String message) {
        super(message);
    }

    public RoleDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleDuplicateException(Errors errors) {
        super(errors);
    }
}
