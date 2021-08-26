package com.example.demo.role.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseNotFoundException;

public class RoleNotFoundException extends BaseNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(Errors errors) {
        super(errors);
    }
}
