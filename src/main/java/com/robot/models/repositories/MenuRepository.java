package com.robot.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.Menu;

public interface MenuRepository extends MongoRepository<Menu, String> {
	Menu findByCourseId(String courseId);
}
