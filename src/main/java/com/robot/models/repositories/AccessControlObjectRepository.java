package com.robot.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.models.AccessControlObject;

public interface AccessControlObjectRepository extends MongoRepository<AccessControlObject, String> {
	public List<AccessControlObject> findByObjectID(String objectid);
}
