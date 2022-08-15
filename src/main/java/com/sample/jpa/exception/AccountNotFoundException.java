package com.sample.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Account")
public class AccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message, RuntimeException cause) {
        super(message, cause);
    }

    public AccountNotFoundException(RuntimeException cause) {
        super(cause);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    @Override
    public RuntimeException getCause() {
        return (RuntimeException) super.getCause();
    }
}
