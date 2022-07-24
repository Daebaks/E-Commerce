package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class ExpiredJwtTokenException extends ApplicationAuthenticationException {

    public ExpiredJwtTokenException(ErrorResponseStatusType responseStatus) {
        super(responseStatus);
    }
}
