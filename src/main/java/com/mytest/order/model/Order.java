package com.mytest.order.model;

import static com.mytest.order.constants.ErrorConstants.AMOUNT_ERROR;
import static com.mytest.order.constants.ErrorConstants.DATE_ERROR;
import static com.mytest.order.constants.ErrorConstants.DESCRIPTION_ERROR;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order class represents an order entity with fields for order ID, description,
 * amount, and transaction date. It includes validation constraints for these
 * fields.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
@Builder
public class Order {

	/**
	 * Unique identifier for the order.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	/**
	 * Description of the order.
	 */
	@NotNull(message = DESCRIPTION_ERROR)
	@Size(min = 5, message = DESCRIPTION_ERROR)
	@Size(max = 50, message = DESCRIPTION_ERROR)
	private String description;

	/**
	 * Amount associated with the order.
	 */
	@NotNull(message = AMOUNT_ERROR)
	@Min(value = 1, message = AMOUNT_ERROR)
	private BigDecimal amount;

	/**
	 * Date when the transaction took place.
	 */
	@NotNull(message = DATE_ERROR)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = DATE_ERROR)
	private LocalDate transactionDate;
}