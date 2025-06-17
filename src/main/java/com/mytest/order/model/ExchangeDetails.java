package com.mytest.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * ExchangeDetails class represents the details of a currency exchange,
 * including the country and currency description, exchange rate, and record date.
 */
@Builder
@Data
public class ExchangeDetails {

	/**
	 * Country and currency description.
	 */
	@JsonProperty("country_currency_desc")
	private String countryCurrencyDesc;

	/**
	 * Exchange rate for the currency.
	 */
	@JsonProperty("exchange_rate")
	private String exchangeRate;

	/**
	 * Date when the exchange rate was recorded.
	 */
	@JsonProperty("record_date")
	private String recordDate;
}