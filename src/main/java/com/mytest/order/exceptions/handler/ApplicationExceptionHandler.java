package com.mytest.order.exceptions.handler;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mytest.order.constants.ApplicationConstants;
import com.mytest.order.constants.MappedError;
import com.mytest.order.exceptions.OrderException;
import com.mytest.order.exceptions.model.ErrorMessage;
import com.mytest.order.model.OrderResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationExceptionHandler handles exceptions thrown by the application. It
 * provides custom error responses for validation errors, general exceptions,
 * and specific application exceptions.
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {

	/**
	 * Handles MethodArgumentNotValidException, which occurs when validation fails
	 * for method arguments annotated with @Valid.
	 *
	 * @param exception the exception that was thrown
	 * @param request   the HTTP request that caused the exception
	 * @return a ResponseEntity containing an OrderResponse with error details
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<OrderResponse> handleMethodArgumentNotValidException(
			final MethodArgumentNotValidException exception, HttpServletRequest request) {

		List<ErrorMessage> erros = exception.getAllErrors().stream().map(error -> {
			String field = null;

			if (error instanceof FieldError fieldError) {
				field = fieldError.getField();
			} else {
				field = error.getObjectName();
			}

			String errorMessage = error.getDefaultMessage();
			MappedError mappedError = MappedError.valueOfLabel(errorMessage);
			return ErrorMessage.builder().errorMessageCode(mappedError.gerErrorCode())
					.errorMessageText(mappedError.getErrorMessage()).additionalErrorMessageText(field).build();
		}).toList();

		log.error("Validation error occurred: {}", erros);
		return new ResponseEntity<>(getOrderResponse(erros), getHeaders(request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles any other exceptions that are not specifically handled by other
	 * exception handlers.
	 *
	 * @param exception the exception that was thrown
	 * @param request   the HTTP request that caused the exception
	 * @return a ResponseEntity containing an OrderResponse with error details
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<OrderResponse> handleAnyError(final Exception exception, HttpServletRequest request) {

		OrderResponse orderResponse = getOrderResponse(Collections.singletonList(
				ErrorMessage.builder().errorMessageText(exception.getMessage()).errorMessageCode(400).build()));

		log.error("An error occurred: {}, returning the composed reponse {}", exception.getMessage(), orderResponse);
		return new ResponseEntity<>(orderResponse, getHeaders(request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles OrderException, which is a custom exception for order-related errors.
	 *
	 * @param exception the OrderException that was thrown
	 * @param request   the HTTP request that caused the exception
	 * @return a ResponseEntity containing an OrderResponse with error details
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<OrderResponse> handleOrderException(final OrderException exception,
			HttpServletRequest request) {

		OrderResponse orderResponse = getOrderResponse(Collections.singletonList(
				ErrorMessage.builder().errorMessageText(exception.getMessage()).errorMessageCode(400).build()));

		log.error("OrderException occurred: {}, returning the composed response {}", exception.getMessage(),
				orderResponse);
		return new ResponseEntity<>(orderResponse, getHeaders(request), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Constructs an OrderResponse with the provided error messages.
	 *
	 * @param errorMessages the list of error messages to include in the response
	 * @return an OrderResponse containing the error messages
	 */
	private OrderResponse getOrderResponse(List<ErrorMessage> errorMessages) {
		return OrderResponse.builder().errors(errorMessages).build();
	}

	/**
	 * Constructs HttpHeaders with the X-Trace-ID header from the request.
	 *
	 * @param request the HTTP request
	 * @return HttpHeaders containing the X-Trace-ID header
	 */
	private HttpHeaders getHeaders(HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(ApplicationConstants.X_TRACE_ID.getMessage(),
				request.getHeader(ApplicationConstants.X_TRACE_ID.getMessage()));

		return headers;
	}
}