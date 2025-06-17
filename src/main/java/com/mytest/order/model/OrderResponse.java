package com.mytest.order.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mytest.order.exceptions.model.ErrorMessage;

import lombok.Builder;
import lombok.Data;

/**
 * OrderResponse class represents the response structure for order details.
 * It includes a list of orders and a list of error messages if any errors occur.
 */
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class OrderResponse {
	
	/**
	 * List of orders containing details about each order.
	 */
	private List<Order> orders;
	
	/**
	 * List of error messages that may occur during the processing of the order.
	 */
	private List<ErrorMessage> errors;
}