package com.robot.bootstrapServer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableSet;
import com.robot.models.Device;
import com.robot.models.Robot;



@RestController
@RequestMapping("/bootstrapServer")
public class BootstrapServerController {
	public static final String robotServerURL =  "http://localhost:8080/server";
	public static ImmutableSet<String> robots = ImmutableSet.of("1ford", "2ford", "3ford");
	
	public static final String tvServerURL =  "http://localhost:8088/server";
	public static ImmutableSet<String> tvs = ImmutableSet.of("1", "2", "3");


	@RequestMapping("/robot")
	public String bootstrapRobot(@RequestBody Device request) {
		System.out.println("Bootstrap server received message is : " + request);
		String id = request.getEndpointClientName();
		if (robots.contains(id)) {
			String response = robotServerURL;
			System.out.println("Replied Message: " + response);
			return response;

		} else {
			System.out.println("Can't find this device in Bootstrap server database");
			System.out.println("Replied Message: null");
			return null;
		}
	}
	
	@RequestMapping("/tv")
	public String bootstrapTV(@RequestBody Robot request) {
		System.out.println("Bootstrap server received message is : " + request);
		String id = request.getRobotId();
		if (robots.contains(id)) {
			String response = robotServerURL;
			System.out.println("Replied Message: " + response);
			return response;

		} else {
			System.out.println("Can't find this device in Bootstrap server database");
			System.out.println("Replied Message: null");
			return null;
		}
	}
}