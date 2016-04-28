package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.OrderHistory;

public interface OrderHistoryRepository extends MongoRepository<OrderHistory, String> {
	public OrderHistory findByCustomerId(String customerId);
}
