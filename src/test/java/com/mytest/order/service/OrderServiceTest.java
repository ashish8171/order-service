package com.mytest.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mytest.order.OrderApplication;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;
import com.mytest.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@SpringBootTest(classes = OrderApplication.class)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@Execution(ExecutionMode.SAME_THREAD)
class OrderServiceTest {

	@MockitoBean
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllOrders() {

		circuitBreakerRegistry.circuitBreaker("OrderService").transitionToClosedState();

		Order mockOrder = new Order();
		when(orderRepository.findAll()).thenReturn(Collections.singletonList(mockOrder));

		OrderResponse response = orderService.getAllOrders();

		assertEquals(1, response.getOrders().size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void testGetOrderByIdSuccess() {
		Order mockOrder = new Order();
		when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

		OrderResponse response = orderService.getOrderById(1L);

		assertEquals(1, response.getOrders().size());
		assertEquals(mockOrder, response.getOrders().get(0));
		verify(orderRepository, times(1)).findById(1L);
	}

	@Test
	void testGetOrderByIdNotFound() {
		when(orderRepository.findById(1L)).thenReturn(Optional.empty());
		OrderResponse response = orderService.getOrderById(1L);
		assertEquals(1, response.getOrders().size());
		verify(orderRepository, times(1)).findById(1L);
	}
	
	@Test
	void testGetOrderByIdNotFoundThrowsException() {
		when(orderRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));
		OrderResponse response = orderService.getOrderById(1L);
		assertEquals(null, response.getOrders());
		verify(orderRepository, times(1)).findById(1L);
	}

	@Test
	void testCreateOrder() {
		Order mockOrder = new Order();
		when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

		OrderResponse response = orderService.createOrder(mockOrder);

		assertEquals(1, response.getOrders().size());
		assertEquals(mockOrder, response.getOrders().get(0));
		verify(orderRepository, times(1)).save(mockOrder);
	}

	@Test
	void testCreateOrderThrowsException() {
		Order mockOrder = new Order();
		when(orderRepository.save(mockOrder)).thenThrow(new RuntimeException("Database error"));

		OrderResponse response = orderService.createOrder(mockOrder);

		assertEquals(1, response.getOrders().size());
		assertEquals(mockOrder, response.getOrders().get(0));
		verify(orderRepository, times(1)).save(mockOrder);
	}
}