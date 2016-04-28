package com.robot.server.services;

import org.springframework.web.client.RestTemplate;

import com.robot.common.LWM2MAttribute;
import com.robot.common.RobotObjectID;
import com.robot.models.Menu;

/**
 * 
 * @author XueChen
 *
 */
public class Operations {
	String url;
	int objectId;
	int objectInstanceId;
	int resourceId;

	public Operations() {
	}

	public Operations(String url) {
		this.url = url;
	}

	public Operations(String url, RobotObjectID objectId, int objectInstanceId, int resourceId) {
		this.url = url;
		this.objectId = objectId.getValue();
		this.objectInstanceId = objectInstanceId;
		this.resourceId = resourceId;
	}

	public Operations(String url, RobotObjectID objectId, int objectInstanceId) {
		this.url = url;
		this.objectId = objectId.getValue();
		this.objectInstanceId = objectInstanceId;
	}

	// update the value of a Resource
	public void write() {
		RestTemplate r = new RestTemplate();
		Menu menu = new Menu();
		menu.setCourseId("1");
		menu.setName("pan cake");
		menu.setDesc("pan cake");
		menu.setPrice(12.5);

		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId;
		// System.out.println("Sending the request (Set Channel = " + i + ") to
		// " + url);
		String s = r.postForObject(url, menu, String.class);
		System.out.println("Receive response message from " + url + ": " + s);
	}

	// access the value of a Resource
	public void read() {
		RestTemplate r = new RestTemplate();
		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId;
		System.out.println("Send read message to " + url);
		Menu menu = r.getForObject(url, Menu.class);
		System.out.println("Receive response message from " + url);
		System.out.println(menu.toString());
	}

	// set step
	public void writeAttribute(int step) {
		RestTemplate r = new RestTemplate();

		LWM2MAttribute attribute = new LWM2MAttribute();
		attribute.setSt(step);

		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId;
		System.out.println("Sending the request: " + url);
		r.put(url, attribute);
	}

	// Cancel observation
	public void writeAttribute(String s) {
		RestTemplate r = new RestTemplate();

		LWM2MAttribute attribute = new LWM2MAttribute();
		attribute.setCancel(s);

		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId;
		System.out.println("Sending the request: " + url);
		r.put(url, attribute);
	}
//discovery the attribute of a resource
	public void discover() {
		RestTemplate r = new RestTemplate();

		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId;
		System.out.println("Send discover message to " + url);
		LWM2MAttribute attribute = r.getForObject(url, LWM2MAttribute.class);
		System.out.println("Receive response message from " + url);
		System.out.println(attribute);
	}
//initial the action of a resource
	public void execute() {
		RestTemplate r = new RestTemplate();

		url += "/" + this.objectId + "/" + this.objectInstanceId + "/" + this.resourceId + "/0";
		System.out.println("Send execute message to " + url);
		String result = r.getForObject(url, String.class);
		// String result = r.postForObject(url, null, String.class);
		System.out.println("Receive response message from " + url);
		System.out.println(result);
	}

	public void create() {
		String s = "create";

		RestTemplate r = new RestTemplate();
		url += "/" + this.objectId + "/" + this.objectInstanceId;
		System.out.println("Sending the request: " + url);

		String response = r.postForObject(url, s, String.class);

		System.out.println("Receiving the response: " + response);

	}

	public void delete() {
		RestTemplate r = new RestTemplate();
		url += "/" + this.objectId + "/" + this.objectInstanceId;
		System.out.println("Sending the request: " + url);

		r.delete(url);
		System.out.println("Receiving the response: ");
	}

	public void observe() {
		RestTemplate r = new RestTemplate();
		url += "/" + objectId + "/" + objectInstanceId + "/" + resourceId;
		System.out.println("Sending the request: " + url);
		String response = r.getForObject(url, String.class);
		System.out.println("Receiving the response: " + response);
		return;
	}
}
