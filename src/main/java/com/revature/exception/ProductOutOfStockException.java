package com.revature.exception;

public class ProductOutOfStockException extends RuntimeException{
	
	public ProductOutOfStockException(String message) {
		super(message);
	}
}
