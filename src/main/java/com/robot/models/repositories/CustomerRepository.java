package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	public Customer findByCustomerId(String customerId);

	public Customer findByName(String name);

	public Customer findByBirthday(String birthday);
}
