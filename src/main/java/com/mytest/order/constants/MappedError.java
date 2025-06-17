package com.mytest.order.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ApplicationConstants is an enum that holds various constants used throughout
 * the application. It includes constants for trace IDs, service names, and
 * filter strings.
 */
public enum MappedError {

	/**
	 * Description error with code 101.
	 */
	DESCRIPTION_ERROR(ErrorConstants.DESCRIPTION_ERROR, 101),

	/**
	 * Amount error with code 102.
	 */
	AMOUNT_ERROR(ErrorConstants.AMOUNT_ERROR, 102),

	/**
	 * Date error with code 103.
	 */
	DATE_ERROR(ErrorConstants.DATE_ERROR, 103),

	/**
	 * Invalid order error with code 104.
	 */
	INVALID_ORDER(ErrorConstants.INVALID_ORDER, 104),

	/**
	 * Unable to create order error with code 105.
	 */
	UBABLE_TO_CREATE_ORDER(ErrorConstants.UBABLE_TO_CREATE_ORDER, 105),

	/**
	 * Unable to retrieve order error with code 106.
	 */
	UBABLE_TO_RETRIEVE_ORDER(ErrorConstants.UBABLE_TO_RETRIEVE_ORDER, 106);

	/**
	 * The message associated with the error.
	 */
	private final String message;

	/**
	 * The error code associated with the error.
	 */
	private final int errorCode;

	MappedError(String message, int errorCode) {
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * A map to hold the errors by their label for quick lookup.
	 */
	private static final Map<String, MappedError> BY_LABEL = new HashMap<>();

	static {
		for (MappedError mappedError : values()) {
			BY_LABEL.put(mappedError.message, mappedError);
		}
	}

	/**
	 * Gets the error message associated with the error.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return message;
	}

	/**
	 * Gets the error code associated with the error.
	 *
	 * @return the error code
	 */
	public int gerErrorCode() {
		return errorCode;
	}

	/**
	 * Returns the MappedError enum constant associated with the given label.
	 *
	 * @param label the label of the error
	 * @return the MappedError enum constant, or null if not found
	 */
	public static MappedError valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}