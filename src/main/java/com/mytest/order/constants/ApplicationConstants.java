package com.mytest.order.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ApplicationConstants is an enum that holds various constants used throughout
 * the application. It includes constants for trace IDs, service names, and
 * filter strings.
 */
public enum ApplicationConstants {

	/**
	 * X-Trace-ID is a constant for the trace ID header used in requests.
	 */
	X_TRACE_ID("X-TRACE-ID"),

	/**
	 * SERVICE_NAME is a constant for the name of the order service.
	 */
	SERVICE_NAME("Order Service"),

	/**
	 * FILTER_STRING is a constant for the filter string format used in API
	 * requests.
	 */
	FILTER_STRING("%s?fields=%s&filter=%s,%s&sort=%s");

	/**
	 * The message associated with the constant.
	 */
	private final String message;

	/**
	 * Constructor for ApplicationConstants.
	 *
	 * @param message the message associated with the constant
	 */
	ApplicationConstants(String message) {
		this.message = message;
	}

	/**
	 * A map to hold the constants by their label for quick lookup.
	 */
	private static final Map<String, ApplicationConstants> BY_LABEL = new HashMap<>();

	static {
		for (ApplicationConstants applicationConstants : values()) {
			BY_LABEL.put(applicationConstants.message, applicationConstants);
		}
	}

	/**
	 * Gets the message associated with the constant.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the ApplicationConstants enum constant associated with the given
	 * label.
	 *
	 * @param label the label of the constant
	 * @return the ApplicationConstants enum constant, or null if not found
	 */
	public static ApplicationConstants valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}