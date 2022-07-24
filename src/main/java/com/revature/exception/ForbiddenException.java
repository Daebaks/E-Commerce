package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class ForbiddenException extends ApplicationAuthenticationException {

    public ForbiddenException(ErrorResponseStatusType responseStatus) {
        super(responseStatus);
    }
}
