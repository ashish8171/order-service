package com.mytest.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytest.order.model.Order;

/**
 * OrderRepository is an interface that extends JpaRepository to provide CRUD
 * operations for the Order entity. It allows for easy interaction with the
 * database to manage order records.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}