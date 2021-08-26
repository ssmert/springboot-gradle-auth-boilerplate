package com.example.demo.user.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseDuplicateException;

public class UserDuplicateException extends BaseDuplicateException {
    public UserDuplicateException(String message) {
        super(message);
    }

    public UserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDuplicateException(Errors errors) {
        super(errors);
    }
}
