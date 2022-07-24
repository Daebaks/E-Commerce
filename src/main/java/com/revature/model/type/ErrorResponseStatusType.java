package com.revature.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorResponseStatusType {

    BLANK_AUTHORIZATION_HEADER_EXCEPTION("Authorization header cannot be blank"),
    MALFORMED_AUTHORIZATION_HEADER_EXCEPTION("Invalid authorization header"),
    MALFORMED_JWT_EXCEPTION("Invalid JWT token"),
    EXPIRED_JWT_EXCEPTION("JWT token expired"),
    INVALID_HASH_EXCEPTION("Invalid hash"),
    USER_NOT_FOUND_EXCEPTION("User [%s] not found"),
    DEAL_NOT_FOUND_EXCEPTION("Deal [%s] not found"),
    EMAIL_ALREADY_CONFIRMED_EXCEPTION("Email has been already confirmed"),
    INVALID_CREDENTIALS_EXCEPTION("Invalid credentials"),
    REMEMBER_ME_EXCEPTION("'Remember me' flag is required"),
    USER_ALREADY_EXISTS_EXCEPTION("User already exists"),
    ARITHMETIC_OPERATION_EXCEPTION("Division by zero is not allowed"),
    INVALID_VALUE_EXCEPTION("Unsupported value [%s]"),
    VALIDATION_EXCEPTION("Validation exception"),
    FORBIDDEN_EXCEPTION("Forbidden request"),
    INTERNAL_SERVER_EXCEPTION("%s");

    private final String message;

    public String getMessage(Object args) {
        return String.format(this.getMessage(), args);
    }
}
