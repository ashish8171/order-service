package com.mytest.order.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytest.order.constants.MappedError;
import com.mytest.order.exceptions.model.ErrorMessage;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;
import com.mytest.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderService is responsible for managing orders in the system. It provides
 * methods to retrieve all orders, get an order by ID, and create a new order.
 * It uses a repository to interact with the database and applies circuit
 * breaker patterns for resilience.
 */
@Slf4j
@Service
public class OrderService {

	/**
	 * OrderRepository is used to perform CRUD operations on Order entities.
	 */
	private OrderRepository orderRepository;

	/**
	 * Constructor for OrderService.
	 *
	 * @param orderRepository the repository to manage orders
	 */
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
	 * Retrieves all orders from the database.
	 *
	 * @return OrderResponse containing a list of all orders
	 */
	public OrderResponse getAllOrders() {
		return OrderResponse.builder().orders(orderRepository.findAll()).build();
	}

	/**
	 * Retrieves an order by its ID.
	 *
	 * @param id the ID of the order to retrieve
	 * @return OrderResponse containing the order details or an empty order if not
	 *         found
	 */
	@Transactional(readOnly = true)
	@CircuitBreaker(name = "OrderService", fallbackMethod = "unableToRetrieveOrder")
	public OrderResponse getOrderById(Long id) {

		log.info("Retrieving order with ID: {}", id);

		Optional<Order> dbOrder = orderRepository.findById(id);

		Order order = dbOrder.isEmpty() ? new Order() : dbOrder.get();
		return OrderResponse.builder().orders(Collections.singletonList(order)).build();
	}

	/**
	 * Creates a new order in the database.
	 *
	 * @param order the order to create
	 * @return OrderResponse containing the created order
	 */
	@Transactional
	@CircuitBreaker(name = "OrderService", fallbackMethod = "unableToCreateOrder")
	public OrderResponse createOrder(Order order) {
		log.info("Creating order: {}", order);
		return OrderResponse.builder().orders(Collections.singletonList(orderRepository.save(order))).build();
	}

	/**
	 * Fallback method for unable to create order scenario.
	 *
	 * @param order the order that failed to be created
	 * @param t     the exception that occurred during order creation
	 * @return OrderResponse containing the error message
	 */
	public OrderResponse unableToCreateOrder(Order order, Throwable t) {
		MappedError error = MappedError.UBABLE_TO_CREATE_ORDER;
		OrderResponse orderResponse = OrderResponse.builder().orders(Collections.singletonList(order))
				.errors(Collections.singletonList(ErrorMessage.builder().errorMessageCode(error.gerErrorCode())
						.errorMessageText(t.getLocalizedMessage() + " " + error.getErrorMessage()).build()))
				.build();

		log.error("Unable to create order: {}, error: {}", orderResponse, t.getMessage());
		return orderResponse;
	}

	/**
	 * Fallback method for unable to retrieve order scenario.
	 *
	 * @param id the ID of the order that failed to be retrieved
	 * @param t  the exception that occurred during order retrieval
	 * @return OrderResponse containing the error message
	 */
	public OrderResponse unableToRetrieveOrder(Long id, Throwable t) {
		MappedError error = MappedError.UBABLE_TO_RETRIEVE_ORDER;
		OrderResponse response = OrderResponse.builder()
				.errors(Collections.singletonList(ErrorMessage.builder().errorMessageCode(error.gerErrorCode())
						.errorMessageText(
								"Please try again with order id " + id + " error is " + error.getErrorMessage())
						.build()))
				.build();
		log.error("Unable to retrieve order with ID {}: {}, error: {}", id, response, t.getMessage());
		
		return response;
	}
}