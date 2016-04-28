package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.Robot;

public interface RobotRepository extends MongoRepository<Robot, String> {
	Robot findByrobotId(String robotId);
}
