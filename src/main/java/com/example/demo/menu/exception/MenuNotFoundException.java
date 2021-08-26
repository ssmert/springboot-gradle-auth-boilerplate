package com.example.demo.menu.exception;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseNotFoundException;

public class MenuNotFoundException extends BaseNotFoundException {
    public MenuNotFoundException(String message) {
        super(message);
    }

    public MenuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuNotFoundException(Errors errors) {
        super(errors);
    }
}
