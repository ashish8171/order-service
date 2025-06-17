package com.mytest.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mytest.order.constants.Currencies;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.model.CurrencyResponse;
import com.mytest.order.model.ExchangeDetails;
import com.mytest.order.model.ExchangeResponse;
import com.mytest.order.model.Order;
import com.mytest.order.model.OrderResponse;

class ExchangeRateMapperServiceTest {

	@Mock
	private OrderService orderService;

	@Mock
	private CurrencyService currencyService;

	@InjectMocks
	private ExchangeRateMapperService exchangeRateMapperService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetTransactionByIdSuccess() {
		Long orderId = 1L;
		Currencies currency = Currencies.AFGANISTAN;
		LocalDate date = LocalDate.now();

		Order order = Order.builder().orderId(orderId).amount(new BigDecimal("100.00")).description("Test Order")
				.transactionDate(date).build();

		OrderResponse orderResponse = OrderResponse.builder().orders(List.of(order)).build();

		ExchangeDetails exchangeDetails = ExchangeDetails.builder().exchangeRate("1.5").build();

		CurrencyResponse currencyResponse = CurrencyResponse.builder().data(List.of(exchangeDetails)).build();

		when(orderService.getOrderById(orderId)).thenReturn(orderResponse);
		when(currencyService.getCurrencyRate(currency, date)).thenReturn(currencyResponse);

		ExchangeResponse response = exchangeRateMapperService.getTransactionById(orderId, currency);

		assertEquals(orderId, response.getOrderId());
		assertEquals(new BigDecimal("100.00"), response.getAmount());
		assertEquals(new BigDecimal("150.00"), response.getConvertedAmount());
		assertEquals(new BigDecimal("1.50"), response.getExchangeRate());
		assertEquals("Test Order", response.getDescription());
		assertEquals(date, response.getTransactionDate());
	}

	@Test
	void testGetTransactionByIdOrderNotFound() {
		Long orderId = 2L;
		when(orderService.getOrderById(orderId)).thenReturn(null);

		OrderException ex = assertThrows(OrderException.class,
				() -> exchangeRateMapperService.getTransactionById(orderId, Currencies.INDIA));
		assertTrue(ex.getMessage().contains("Order with ID " + orderId + " not found."));
	}

	@Test
	void testGetTransactionByIdEmptyOrderList() {
		Long orderId = 3L;
		OrderResponse orderResponse = OrderResponse.builder().orders(Collections.emptyList()).build();
		when(orderService.getOrderById(orderId)).thenReturn(orderResponse);

		OrderException ex = assertThrows(OrderException.class,
				() -> exchangeRateMapperService.getTransactionById(orderId, Currencies.INDIA));
		assertTrue(ex.getMessage().contains("Order with ID " + orderId + " not found."));
	}

	@Test
	void testGetTransactionByIdExchangeRateNotFound() {
		Long orderId = 4L;
		LocalDate date = LocalDate.now();

		Order order = Order.builder().orderId(orderId).amount(new BigDecimal("50.00")).description("Order")
				.transactionDate(date).build();

		OrderResponse orderResponse = OrderResponse.builder().orders(List.of(order)).build();

		when(orderService.getOrderById(orderId)).thenReturn(orderResponse);
		when(currencyService.getCurrencyRate(Currencies.EURO_ZONE, date)).thenReturn(null);

		OrderException ex = assertThrows(OrderException.class,
				() -> exchangeRateMapperService.getTransactionById(orderId, Currencies.EURO_ZONE));
		assertTrue(ex.getMessage().contains("The purchase cannot be converted to the target currency"));
	}

	@Test
	void testGetTransactionByIdExchangeRateDataEmpty() {
		Long orderId = 5L;
		LocalDate date = LocalDate.now();

		Order order = Order.builder().orderId(orderId).amount(new BigDecimal("75.00")).description("Order")
				.transactionDate(date).build();

		OrderResponse orderResponse = OrderResponse.builder().orders(List.of(order)).build();

		CurrencyResponse currencyResponse = CurrencyResponse.builder().data(Collections.emptyList()).build();

		when(orderService.getOrderById(orderId)).thenReturn(orderResponse);
		when(currencyService.getCurrencyRate(Currencies.EURO_ZONE, date)).thenReturn(currencyResponse);

		OrderException ex = assertThrows(OrderException.class,
				() -> exchangeRateMapperService.getTransactionById(orderId, Currencies.EURO_ZONE));
		assertTrue(ex.getMessage().contains("The purchase cannot be converted to the target currency"));
	}
}