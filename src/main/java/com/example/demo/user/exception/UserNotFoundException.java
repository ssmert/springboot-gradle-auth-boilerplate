package com.example.demo.user.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseNotFoundException;

public class UserNotFoundException extends BaseNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Errors errors) {
        super(errors);
    }
}
