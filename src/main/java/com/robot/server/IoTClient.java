package com.robot.server;

import org.springframework.data.annotation.Id;

public class IoTClient {
	@Id
	private String endpointClientName;
	private int lifetime;
	private String lWM2MVersion;
	private String bindingMode;
	private String sMSNumber;
	private String objectsAndObjectInstances;

	public IoTClient() {
	}

	public IoTClient(String endpointClientName, int lifetime, String LWM2MVersion, String bindingMode, String SMSNumber,
			String objectsAndObjectInstances) {
		this.endpointClientName = endpointClientName;
		this.lifetime = lifetime;
		this.lWM2MVersion = LWM2MVersion;
		this.bindingMode = bindingMode;
		this.sMSNumber = SMSNumber;
		this.objectsAndObjectInstances = objectsAndObjectInstances;
	}

	public String getEndpointClientName() {
		return endpointClientName;
	}

	public void setEndpointClientName(String endpointClientName) {
		this.endpointClientName = endpointClientName;
	}

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	public String getLWM2MVersion() {
		return lWM2MVersion;
	}

	public void setLWM2MVersion(String lWM2MVersion) {
		lWM2MVersion = lWM2MVersion;
	}

	public String getBindingMode() {
		return bindingMode;
	}

	public void setBindingMode(String bindingMode) {
		this.bindingMode = bindingMode;
	}

	public String getSMSNumber() {
		return sMSNumber;
	}

	public void setSMSNumber(String sMSNumber) {
		sMSNumber = sMSNumber;
	}

	public String getObjectsAndObjectInstances() {
		return objectsAndObjectInstances;
	}

	public void setObjectsAndObjectInstances(String objectsAndObjectInstances) {
		this.objectsAndObjectInstances = objectsAndObjectInstances;
	}

	@Override
	public String toString() {
		return "IoTClient: {" + "endpointClientName='" + endpointClientName + '\'' + ", lifetime=" + lifetime
				+ ", LWM2MVersion='" + lWM2MVersion + '\'' + ", bindingMode='" + bindingMode + '\'' + ", SMSNumber='"
				+ sMSNumber + '\'' + ", objectsAndObjectInstances='" + objectsAndObjectInstances + '\'' + '}';
	}

}
