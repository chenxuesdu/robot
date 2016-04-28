package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.RobotAttributeObject;

public interface RobotAttributeObjectRepository extends MongoRepository<RobotAttributeObject, String> {

}
