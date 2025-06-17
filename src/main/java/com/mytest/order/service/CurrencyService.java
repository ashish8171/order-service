package com.mytest.order.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mytest.order.constants.ApplicationConstants;
import com.mytest.order.constants.Currencies;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.model.CurrencyResponse;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

/**
 * CurrencyService is responsible for fetching currency exchange rates from an
 * external service. It uses WebClient to make HTTP requests and handles retries
 * with a fallback method.
 */
@Slf4j
@Service
public class CurrencyService {

	/**
	 * WebClient.Builder is used to create instances of WebClient for making HTTP
	 * requests.
	 */
	private final WebClient.Builder webClientBuilder;

	/**
	 * Base URL for the currency conversion service.
	 */
	@Value("${currency.conversion.baseUrl}")
	private String currencyServiceBaseUrl;

	/**
	 * Fields to be included in the currency conversion request.
	 */
	@Value("${currency.conversion.fields}")
	private String currencyServiceFields;

	/**
	 * Filters for the currency conversion request, including currency code, date
	 * range, and sorting.
	 */
	@Value("${currency.conversion.currency-filter}")
	private String currencyServiceCurrencyFilter;

	/**
	 * Date filter for the currency conversion request, specifying the start and end
	 * dates.
	 */
	@Value("${currency.conversion.date-filter}")
	private String currencyServiceDateFilter;

	/**
	 * Sort filter for the currency conversion request.
	 */
	@Value("${currency.conversion.record-sort-filter}")
	private String currencyServiceSortFilter;
	
	@Value("${currency.conversion.lookback-period-in-months:6}")
	private String lookBackMonths;

	/**
	 * Constructor for CurrencyService.
	 *
	 * @param webClientBuilder the WebClient.Builder used to create WebClient
	 *                         instances
	 */
	public CurrencyService(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	/**
	 * Retrieves the currency exchange rate for a specified currency code and
	 * purchase date.
	 *
	 * @param currencyCode the currency code to retrieve the exchange rate for
	 * @param purchaseDate the date of the purchase transaction
	 * @return CurrencyResponse containing the exchange rate details
	 * @throws OrderException if the exchange rate cannot be retrieved or is not
	 *                        found
	 */
	@Retry(name = "currency-service", fallbackMethod = "handleCurrencyFallback")
	public CurrencyResponse getCurrencyRate(Currencies currencyCode, LocalDate purchaseDate) {
		String url = getCurrencyServiceUrl(currencyCode, purchaseDate);
		log.info("Fetching currency rate from URL: {}", url);
		return webClientBuilder.build().get().uri(url).retrieve().bodyToMono(CurrencyResponse.class).block();
	}

	/**
	 * Constructs the URL for the currency service request based on the provided
	 * currency code and purchase date.
	 *
	 * @param currencyCode the currency code to filter by
	 * @param purchaseDate the date of the purchase transaction
	 * @return a formatted URL string for the currency service request
	 */
	private String getCurrencyServiceUrl(Currencies currencyCode, LocalDate purchaseDate) {
		return String.format(ApplicationConstants.FILTER_STRING.getMessage(), currencyServiceBaseUrl,
				currencyServiceFields,
				currencyServiceCurrencyFilter.replace("dynamic_currency", currencyCode.getCurrency()),
				currencyServiceDateFilter.replace("beginDate", purchaseDate.minusMonths(6).toString())
						.replace("endDate", purchaseDate.toString()),
				currencyServiceSortFilter);
	}

	/**
	 * Fallback method for handling errors when retrieving currency exchange rates.
	 *
	 * @param currencyCode the currency code for which the exchange rate was
	 *                     requested
	 * @param purchaseDate the date of the purchase transaction
	 * @param t            the Throwable that caused the fallback
	 * @return CurrencyResponse with an error message
	 * @throws OrderException if the purchase cannot be converted to the target
	 *                        currency
	 */
	public CurrencyResponse handleCurrencyFallback(Currencies currencyCode, LocalDate purchaseDate, Throwable t) {
		throw new OrderException(
				"The purchase cannot be converted to the target currency " + currencyCode.getCurrency());
	}
}