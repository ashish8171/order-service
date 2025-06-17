package com.mytest.order.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * CurrencyResponse class represents the response structure for currency exchange
 * details.
 */
@Data
@Builder
public class CurrencyResponse {
	
	/**
	 * List of exchange details containing information about currency exchanges.
	 */
	private List<ExchangeDetails> data;
}