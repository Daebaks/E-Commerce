package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class BadCredentialsException extends ApplicationAuthenticationException {

    public BadCredentialsException(ErrorResponseStatusType errorResponseStatus) {
        super(errorResponseStatus);
    }
}
