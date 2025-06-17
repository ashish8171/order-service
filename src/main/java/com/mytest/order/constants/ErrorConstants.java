package com.mytest.order.constants;

/**
 * ErrorConstants class holds the error messages used in the application.
 */
public class ErrorConstants {

	private ErrorConstants() {
	}

	/**
	 * Error messages for Description validation.
	 */
	public static final String DESCRIPTION_ERROR = "Order Description cannot be emtpy, less than 5 characters or exceeds max 50 characters";

	/**
	 * Error messages for Amount validation.
	 */
	public static final String AMOUNT_ERROR = "Order amount must be greater than 0";

	/**
	 * Error messages for Date validation.
	 */
	public static final String DATE_ERROR = "Order Date must be current or future and cannot be empty or null";

	/**
	 * Error messages for Order validation.
	 */
	public static final String INVALID_ORDER = "You must have at least one Order to order";

	/**
	 * Error messages for Order creation and retrieval.
	 */
	public static final String UBABLE_TO_CREATE_ORDER = "Unable to Create Order, please try again";

	/**
	 * Error messages for Order retrieval.
	 */
	public static final String UBABLE_TO_RETRIEVE_ORDER = "Unable to Retrieve Order, please try again";
}