package com.robot.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robot.server.IoTClient;

/**
 * 
 * @author XueChen
 *
 */
public interface IoTClientRepository extends MongoRepository<IoTClient, String> {
    public IoTClient findByEndpointClientName(String s);

}
