package com.guld.sciq.exception.auth;


import org.springframework.security.core.AuthenticationException;

import com.guld.sciq.exception.ErrorMessage;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super(ErrorMessage.INVALID_TOKEN_EXCEPTION);
    }
}
