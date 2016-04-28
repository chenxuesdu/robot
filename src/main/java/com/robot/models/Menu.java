package com.robot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu")
public class Menu {
	@Id
	private String id;
	private String courseId;
	private final int thisObjectID = 10;
	private int thisObjectInstanceID;
	private String courseName;
	private String material;
	private double price;

	public Menu() {

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getThisObjectID() {
		return thisObjectID;
	}

	public int getThisObjectInstanceID() {
		return thisObjectInstanceID;
	}

	public void setThisObjectInstanceID(int thisObjectInstanceID) {
		this.thisObjectInstanceID = thisObjectInstanceID;
	}

	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getName() {
		return courseName;
	}

	public void setName(String name) {
		this.courseName = name;
	}

	public String getDesc() {
		return material;
	}

	public void setDesc(String desc) {
		this.material = desc;
	}

	public String toString() {
		return "Menu: {courseId:" + this.courseId + " courseName: " + this.courseName + " Material: " + this.material + " price: " + this.price
				+ "}";
	}

	public void execute(int arg) {
		switch (arg) {
		case 0:
			print();
			break;
		case 1:
			increasePrice();
			break;
		default:
			print();
		}
	}

	public void print() {
		System.out.println("Menu: {courseId:" + this.courseId + " courseName: " + this.courseName + " Desc: " + this.material + " price: "
				+ this.price + "}");
	}

	public void increasePrice() {
		this.price++;
	}

}
