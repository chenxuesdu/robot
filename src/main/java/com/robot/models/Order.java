package com.robot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
	@Id
	private String id;
	private String customerId;
	private String[] courses;
	private String date;

	public String getCustomerid() {
		return customerId;
	}

	public void setCustomerid(String customerId) {
		this.customerId = customerId;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
