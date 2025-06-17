package com.mytest.order.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.order.OrderApplication;
import com.mytest.order.constants.Currencies;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.exceptions.handler.ApplicationExceptionHandler;
import com.mytest.order.model.ExchangeResponse;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;
import com.mytest.order.repository.OrderRepository;
import com.mytest.order.service.ExchangeRateMapperService;
import com.mytest.order.service.OrderService;

@SpringBootTest(classes = OrderApplication.class)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@Execution(ExecutionMode.SAME_THREAD)
class OrderControllerTest {

	private MockMvc mockMvc;

	@MockitoBean
	private OrderService orderService;

	@MockitoBean
	private ExchangeRateMapperService exchangeRateMapperService;

	@Autowired
	private OrderController orderController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private OrderRepository orderRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		orderController = new OrderController(orderService, exchangeRateMapperService);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController)
				.setControllerAdvice(ApplicationExceptionHandler.class).build();
	}

	@Test
	void testGetAllTransactions() throws Exception {
		OrderResponse mockResponse = OrderResponse.builder().build();
		when(orderService.getAllOrders()).thenReturn(mockResponse);

		mockMvc.perform(get("/api/v1/purchase/order")).andExpect(status().isOk());
	}

	@Test
	void testCreateOrder() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setDescription("Test Order");
		mockOrder.setTransactionDate(LocalDate.now());

		OrderResponse mockResponse = OrderResponse.builder().build();
		when(orderService.createOrder(mockOrder)).thenReturn(mockResponse);

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isCreated());
	}

	@Test
	void testCreateOrderValidationFailedForAmount() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setDescription("Test Order");
		mockOrder.setTransactionDate(LocalDate.now());

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForNegativeAmount() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("-100.00"));
		mockOrder.setDescription("Test Order");
		mockOrder.setTransactionDate(LocalDate.now());

		OrderResponse mockResponse = OrderResponse.builder().build();
		when(orderService.createOrder(mockOrder)).thenReturn(mockResponse);

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForZeroAmount() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("0.00"));
		mockOrder.setDescription("Test Order");
		mockOrder.setTransactionDate(LocalDate.now());

		OrderResponse mockResponse = OrderResponse.builder().build();
		when(orderService.createOrder(mockOrder)).thenReturn(mockResponse);

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForDescription() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setTransactionDate(LocalDate.now());

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForDescriptionLessCharacters() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setDescription("Test");
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setTransactionDate(LocalDate.now());

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForDescriptionMoreCharacters() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setDescription(
				"Test Order with a very long description that exceeds the maximum allowed length for the description field");
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setTransactionDate(LocalDate.now());

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForDate() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setDescription("Test Order");

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrderValidationFailedForDateInPast() throws Exception {
		Order mockOrder = new Order();
		mockOrder.setAmount(new BigDecimal("100.00"));
		mockOrder.setDescription("Test Order");
		mockOrder.setTransactionDate(LocalDate.now().minusDays(10));

		mockMvc.perform(post("/api/v1/purchase/order/createOrder").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mockOrder))).andExpect(status().isBadRequest());
	}

	@Test
	void testGetTransactionById() throws Exception {
		Long orderId = 1L;

		ExchangeResponse exchangeResponse = ExchangeResponse.builder().orderId(1L).amount(new BigDecimal("10.00"))
				.convertedAmount(new BigDecimal("708.6")).exchangeRate(new BigDecimal("70.86"))
				.description("A Description").transactionDate(LocalDate.now()).build();

		OrderResponse mockResponse = OrderResponse.builder().build();
		when(orderService.getOrderById(orderId)).thenReturn(mockResponse);
		when(exchangeRateMapperService.getTransactionById(orderId, Currencies.AFGANISTAN))
				.thenReturn(exchangeResponse); // Assuming null is a valid response for this test

		mockMvc.perform(get("/api/v1/purchase/order/{id}/{currencyCode}", orderId, Currencies.AFGANISTAN.toString()))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetTransactionByIdInvalidCurrencyCode() throws Exception {
		Long orderId = 1L;
		mockMvc.perform(get("/api/v1/purchase/order/{id}/{currencyCode}", orderId, "RandomCurrency"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testApplicationThrowsException() throws Exception {
		Long orderId = 1L;
		when(orderService.getOrderById(orderId)).thenThrow(new RuntimeException("Serevice Unavailable"));

		mockMvc.perform(get("/api/v1/purchase/order/{id}", orderId)).andExpect(status().isBadRequest());
	}

	@Test
	void testApplicationThrowsOrderException() throws Exception {
		Long orderId = 1L;
		when(orderService.getOrderById(orderId)).thenThrow(new OrderException("Serevice Unavailable"));

		mockMvc.perform(get("/api/v1/purchase/order/{id}", orderId)).andExpect(status().isBadRequest());
	}
}