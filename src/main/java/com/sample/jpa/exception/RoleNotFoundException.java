package com.sample.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such role")
public class RoleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RoleNotFoundException(String message, RuntimeException cause) {
        super(message, cause);
    }

    public RoleNotFoundException(RuntimeException cause) {
        super(cause);
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    @Override
    public RuntimeException getCause() {
        return (RuntimeException) super.getCause();
    }
}
