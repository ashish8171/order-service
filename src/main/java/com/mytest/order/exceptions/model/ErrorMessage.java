package com.mytest.order.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * ErrorMessage class represents the structure of an error message that can be
 * returned in the response when an error occurs.
 */
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class ErrorMessage {
	/**
	 * Error code representing the type of error.
	 */
	private int errorMessageCode;

	/**
	 * Textual description of the error.
	 */
	private String errorMessageText;

	/**
	 * Additional error message text that provides more context or details about the
	 * error.
	 */
	private String additionalErrorMessageText;
}