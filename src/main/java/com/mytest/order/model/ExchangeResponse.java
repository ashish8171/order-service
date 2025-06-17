package com.mytest.order.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/**
 * ExchangeResponse class represents the response structure for currency
 * exchange details. It includes the order ID, description, amount, transaction
 * date, exchange rate, and converted amount.
 */
@Builder
@Data
public class ExchangeResponse {

	/**
	 * Unique identifier for the order associated with the exchange.
	 */
	private Long orderId;

	/**
	 * Description of the order or transaction.
	 */
	private String description;

	/**
	 * Amount involved in the transaction.
	 */
	private BigDecimal amount;

	/**
	 * Date when the transaction took place.
	 */
	private LocalDate transactionDate;

	/**
	 * Exchange rate applied for the currency conversion.
	 */
	private BigDecimal exchangeRate;

	/**
	 * Amount converted based on the exchange rate.
	 */
	private BigDecimal convertedAmount;
}