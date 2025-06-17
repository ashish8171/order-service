package com.mytest.order.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.mytest.order.constants.Currencies;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.model.CurrencyResponse;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class CurrencyServiceTest {

	private CurrencyService currencyService;

	private MockWebServer mockWebServer;

	@BeforeEach
	void setUp() throws IOException {

		mockWebServer = new MockWebServer();
		currencyService = new CurrencyService(WebClient.builder());

		// Set required @Value fields via reflection
		ReflectionTestUtils.setField(currencyService, "currencyServiceBaseUrl",
				"http://localhost:" + mockWebServer.getPort());
		ReflectionTestUtils.setField(currencyService, "currencyServiceFields", "fields");
		ReflectionTestUtils.setField(currencyService, "currencyServiceCurrencyFilter", "currency=dynamic_currency");
		ReflectionTestUtils.setField(currencyService, "currencyServiceDateFilter", "date=beginDate:endDate");
		ReflectionTestUtils.setField(currencyService, "currencyServiceSortFilter", "sort=desc");
	}

	@Test
	void testGetCurrencyRateSuccess() {

		mockWebServer.enqueue(new MockResponse().setResponseCode(200)
				.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).setBody(
						"{\"data\":[{\"country_currency_desc\":\"Afghanistan-Afghani\",\"exchange_rate\":\"70.86\",\"record_date\""
								+ ":\"2025-03-31\"}],\"meta\":{\"count\":1,\"labels\":{\"country_currency_desc\":\"Country - Currency Description\","
								+ "\"exchange_rate\":\"Exchange Rate\",\"record_date\":\"Record Date\"},\"dataTypes\":{\"country_currency_desc\""
								+ ":\"STRING\",\"exchange_rate\":\"NUMBER\",\"record_date\":\"DATE\"},\"dataFormats\":{\"country_currency_desc\""
								+ ":\"String\",\"exchange_rate\":\"10.2\",\"record_date\":\"YYYY-MM-DD\"},\"total-count\":1,\"total-pages\":1},\"links\""
								+ ":{\"self\":\"&page%5Bnumber%5D=1&page%5Bsize%5D=100\",\"first\":\"&page%5Bnumber%5D=1&page%5Bsize%5D=100\",\"prev\":null,"
								+ "\"next\":null,\"last\":\"&page%5Bnumber%5D=1&page%5Bsize%5D=100\"}}"));

		CurrencyResponse response = currencyService.getCurrencyRate(Currencies.AFGANISTAN, LocalDate.now());
		assertNotNull(response);
	}

	@Test
	void testHandleCurrencyFallbackThrowsOrderException() {
		LocalDate date = LocalDate.now();
		Exception cause = new RuntimeException("Service down");
		OrderException ex = assertThrows(OrderException.class,
				() -> currencyService.handleCurrencyFallback(Currencies.EURO_ZONE, date, cause));
		assertTrue(ex.getMessage().contains("The purchase cannot be converted to the target currency Euro Zone-Euro"));
	}

	@Test
	void testGetCurrencyServiceUrlFormatsCorrectly() throws Exception {
		// Use reflection to access private method
		var method = CurrencyService.class.getDeclaredMethod("getCurrencyServiceUrl", Currencies.class,
				LocalDate.class);
		method.setAccessible(true);
		LocalDate date = LocalDate.of(2024, 6, 1);
		String url = (String) method.invoke(currencyService, Currencies.AFGANISTAN, date);
		assertTrue(url.contains("http://localhost:" + mockWebServer.getPort()));
		assertTrue(url.contains("Afghanistan-Afghani"));
		assertTrue(url.contains("date="));
		assertTrue(url.contains("sort=desc"));
	}
}