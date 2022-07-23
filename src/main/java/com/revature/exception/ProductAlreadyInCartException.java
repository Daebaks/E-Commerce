package com.revature.exception;

public class ProductAlreadyInCartException extends RuntimeException{
	
	public ProductAlreadyInCartException(String message) {
		super(message);
	}
}
