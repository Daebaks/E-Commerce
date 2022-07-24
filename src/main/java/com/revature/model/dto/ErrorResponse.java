package com.revature.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revature.exception.ApplicationException;
import com.revature.model.type.ErrorResponseStatusType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private long timestamp;

    private String message;

    private ErrorResponseStatusType responseStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object messageParams;

    public static ErrorResponse of(ApplicationException e) {
        return new ErrorResponse(Instant.now().getEpochSecond(), e.getMessage(), e.getResponseStatus(), e.getMessageParams());
    }

    public static ErrorResponse of(String message) {
        return new ErrorResponse(Instant.now().getEpochSecond(), message, ErrorResponseStatusType.FORBIDDEN_EXCEPTION, null);
    }
}
