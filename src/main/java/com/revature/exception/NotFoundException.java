package com.revature.exception;


import com.revature.model.type.ErrorResponseStatusType;

public class NotFoundException extends ApplicationException {

    public NotFoundException(ErrorResponseStatusType statusType, Object args) {
        super(statusType, args);
    }
}
