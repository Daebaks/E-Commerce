package com.revature.exception;

import com.revature.model.type.ErrorResponseStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private ErrorResponseStatusType responseStatus;

    private Object messageParams;

    public ApplicationException(ErrorResponseStatusType responseStatus) {
        super(responseStatus.getMessage());
        this.responseStatus = responseStatus;
    }

    public ApplicationException(ErrorResponseStatusType responseStatus, Object args) {
        super(responseStatus.getMessage(args));
        this.responseStatus = responseStatus;
    }

    public ApplicationException(ErrorResponseStatusType responseStatus, List<?> messageParams) {
        super(responseStatus.getMessage());
        this.responseStatus = responseStatus;
        this.messageParams = messageParams;
    }
}
