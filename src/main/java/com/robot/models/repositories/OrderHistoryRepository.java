package com.robot.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.OrderHistory;

public interface OrderHistoryRepository extends MongoRepository<OrderHistory, String> {
	public List<OrderHistory> findByCustomerId(String customerId);
}
