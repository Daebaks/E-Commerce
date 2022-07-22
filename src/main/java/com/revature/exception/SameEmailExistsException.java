package com.revature.exception;

public class SameEmailExistsException extends RuntimeException {

	public SameEmailExistsException(String message) {
		super(message);
	}
}
