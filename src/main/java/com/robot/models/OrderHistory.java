package com.robot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class OrderHistory {
	@Id
	private String id;
	private String customerId;
	private String[] courses;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}
}
