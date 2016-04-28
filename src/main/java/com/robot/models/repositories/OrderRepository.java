package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	public Order findByCustomerId(String customerId);
}
