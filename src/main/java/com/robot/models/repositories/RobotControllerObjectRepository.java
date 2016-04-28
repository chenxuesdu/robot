package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.RobotControllerObject;

public interface RobotControllerObjectRepository extends MongoRepository<RobotControllerObject, String> {

}
