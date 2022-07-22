package com.revature.exception;

public class UserNameAlreadyTakenException  extends RuntimeException {
	 
	public UserNameAlreadyTakenException(String message) {
		super(message);
	}
}
