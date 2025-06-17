package com.mytest.order.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.mytest.order.constants.Currencies;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.model.CurrencyResponse;
import com.mytest.order.model.ExchangeDetails;
import com.mytest.order.model.ExchangeResponse;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * ExchangeRateMapperService is responsible for mapping exchange rates to
 * orders. It fetches the order by ID and retrieves the exchange rate for a
 * specified currency, then calculates the converted amount based on the
 * exchange rate.
 */
@Slf4j
@Service
public class ExchangeRateMapperService {

	/**
	 * OrderService is used to manage purchase orders.
	 */
	private final OrderService orderService;

	/**
	 * CurrencyService is used to fetch currency exchange rates.
	 */
	private final CurrencyService currencyService;

	/**
	 * Constructor for ExchangeRateMapperService.
	 *
	 * @param orderService    the service to manage orders
	 * @param currencyService the service to fetch currency exchange rates
	 */
	public ExchangeRateMapperService(OrderService orderService, CurrencyService currencyService) {
		this.orderService = orderService;
		this.currencyService = currencyService;
	}

	/**
	 * Maps exchange rates to orders by fetching the order by ID and retrieving the
	 * exchange rate for a specified currency code. It calculates the converted
	 * amount based on the exchange rate and returns an ExchangeResponse.
	 *
	 * @param orderId      the ID of the order to map exchange rates to
	 * @param currencyCode the currency code to convert the order amount to
	 * @return ExchangeResponse containing the order details and converted amount
	 */
	public ExchangeResponse getTransactionById(Long orderId, Currencies currencyCode) {

		log.info("ExchangeRateMapperService - getTransactionById called with orderId: {} and currencyCode: {}", orderId,
				currencyCode);

		// Fetch the order by ID
		OrderResponse orderResponse = orderService.getOrderById(orderId);

		if (ObjectUtils.isEmpty(orderResponse) || CollectionUtils.isEmpty(orderResponse.getOrders())) {
			throw new OrderException("Order with ID " + orderId + " not found.");
		} else {

			List<Order> orders = orderResponse.getOrders();

			Order order = orders.get(0);
			// Get the exchange rate for the specified currency code
			CurrencyResponse exchangeRate = currencyService.getCurrencyRate(currencyCode, order.getTransactionDate());

			if (ObjectUtils.isEmpty(exchangeRate) || CollectionUtils.isEmpty(exchangeRate.getData())) {
				throw new OrderException(
						"The purchase cannot be converted to the target currency " + currencyCode.getCurrency());
			}

			log.debug("ExchangeRateMapperService - Exchange rate data retrieved: {}", exchangeRate);

			List<ExchangeDetails> data = exchangeRate.getData();

			BigDecimal exchangeRateValue = new BigDecimal(data.get(0).getExchangeRate());
			exchangeRateValue = exchangeRateValue.setScale(2, RoundingMode.HALF_UP);

			BigDecimal calculatedAmount = order.getAmount().multiply(exchangeRateValue);
			calculatedAmount = calculatedAmount.setScale(2, RoundingMode.HALF_UP);

			ExchangeResponse response = ExchangeResponse.builder().orderId(order.getOrderId()).amount(order.getAmount())
					.convertedAmount(calculatedAmount).exchangeRate(exchangeRateValue)
					.description(order.getDescription()).transactionDate(order.getTransactionDate()).build();

			log.debug("ExchangeRateMapperService - Returning ExchangeResponse: {}", response);
			return response;
		}
	}
}