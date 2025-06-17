package com.mytest.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.order.constants.Currencies;
import com.mytest.order.model.ExchangeResponse;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;
import com.mytest.order.service.ExchangeRateMapperService;
import com.mytest.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderController handles requests related to purchase orders. It provides
 * endpoints to create orders, retrieve all orders, and get exchange rates for a
 * specific order.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/purchase/order")
public class OrderController {

	/**
	 * OrderService is used to manage purchase orders.
	 */
	private final OrderService orderService;

	/**
	 * ExchangeRateMapperService is used to map exchange rates to orders.
	 */
	private final ExchangeRateMapperService exchangeRateMapperService;

	public OrderController(OrderService orderService, ExchangeRateMapperService exchangeRateMapperService) {
		this.orderService = orderService;
		this.exchangeRateMapperService = exchangeRateMapperService;
	}

	/**
	 * Retrieves all purchase orders.
	 *
	 * @return OrderResponse containing a list of all orders.
	 */
	@GetMapping
	public OrderResponse getAllTransactions() {
		log.info(OrderController.class.getSimpleName() + " - getAllTransactions called");
		return orderService.getAllOrders();
	}

	/**
	 * Retrieves a specific purchase order by its ID.
	 *
	 * @param id the ID of the order to retrieve.
	 * @return OrderResponse containing the order details.
	 */
	@PostMapping("/createOrder")
	@ResponseStatus(value = HttpStatus.CREATED)
	public OrderResponse createOrder(@RequestBody @Valid Order order) {
		log.info(OrderController.class.getSimpleName() + " - createOrder called with order: {}", order);
		return orderService.createOrder(order);
	}

	/**
	 * Retrieves a specific purchase order by its ID and converts it to the
	 * specified currency.
	 *
	 * @param id           the ID of the order to retrieve.
	 * @param currencyCode the currency code to convert the order amount to.
	 * @return ExchangeResponse containing the converted order details.
	 */
	@GetMapping("/{id}/{currencyCode}")
	public ExchangeResponse getTransactionById(@PathVariable Long id, @PathVariable @Valid Currencies currencyCode) {
		log.info(
				OrderController.class.getSimpleName() + " - getTransactionById called with id: {} and currencyCode: {}",
				id, currencyCode);
		return exchangeRateMapperService.getTransactionById(id, currencyCode);
	}
}