
package com.robot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @author XueChen
 *
 */
@Document(collection="robot")
public class Robot {
	@Id
	private String id;
	private String robotId;
	private String manufacturer;
	private String modelNumber;
	private String serialNumber;
	private boolean boostrapStatus;
	private boolean registerStatus;
	private String serverURL;

	public Robot() {
	}

	public Robot(String robotId, String manufacturer, String modelNumber, String serialNumber) {
		super();
		this.robotId = robotId;
		this.manufacturer = manufacturer;
		this.modelNumber = modelNumber;
		this.serialNumber = serialNumber;
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isBoostrapStatus() {
		return boostrapStatus;
	}

	public void setBoostrapStatus(boolean boostrapStatus) {
		this.boostrapStatus = boostrapStatus;
	}

	public boolean isRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(boolean registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	@Override
	public String toString() {
		return "Robot {" + "robotId='" + robotId + '\'' + ", Manufacturer='" + manufacturer + '\'' + ", ModelNumber='"
				+ modelNumber + '\'' + ", SerialNumber='" + serialNumber + '\'' + '}';
	}
}
