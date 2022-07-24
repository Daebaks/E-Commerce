package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class MalformedJwtTokenException extends ApplicationAuthenticationException {

    public MalformedJwtTokenException(ErrorResponseStatusType responseStatus) {
        super(responseStatus);
    }
}
