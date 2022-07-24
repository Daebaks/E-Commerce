package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class BlankAuthorizationHeaderException extends ApplicationAuthenticationException {

    public BlankAuthorizationHeaderException(ErrorResponseStatusType responseStatus) {
        super(responseStatus);
    }
}
