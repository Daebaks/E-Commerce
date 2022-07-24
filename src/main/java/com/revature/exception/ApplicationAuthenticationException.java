package com.revature.exception;

import com.revature.model.type.ErrorResponseStatusType;
import org.springframework.security.core.AuthenticationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationAuthenticationException extends AuthenticationException {

	private ErrorResponseStatusType responseStatus;

	public ApplicationAuthenticationException(ErrorResponseStatusType responseStatus) {
		super(responseStatus.getMessage());
		this.responseStatus = responseStatus;
	}

	public ApplicationAuthenticationException(ErrorResponseStatusType responseStatus, String arg) {
		super(responseStatus.getMessage());
		this.responseStatus = responseStatus;
	}
}
