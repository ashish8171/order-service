package com.mytest.order.exceptions;

/**
 * Custom exception class for handling order-related exceptions.
 * This class extends RuntimeException to allow for unchecked exceptions.
 */
public class OrderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderException(String message) {
		super(message);

	}
}