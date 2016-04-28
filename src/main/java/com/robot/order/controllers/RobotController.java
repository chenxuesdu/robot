package com.robot.order.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.robot.common.LWM2MAttribute;
import com.robot.common.RobotObjectID;
import com.robot.models.Device;
import com.robot.models.Menu;
import com.robot.models.Robot;
import com.robot.models.RobotAttributeObject;
import com.robot.models.RobotControllerObject;
import com.robot.models.repositories.AccessControlObjectRepository;
import com.robot.models.repositories.MenuRepository;
import com.robot.models.repositories.RobotAttributeObjectRepository;
import com.robot.models.repositories.RobotControllerObjectRepository;
import com.robot.models.repositories.RobotRepository;

@RestController
public class RobotController {

	private static boolean observed = false; // true: observed, false: not
												// observed

	private static final String BoostrapServerURL = "http://localhost:8080/bootstrapServer";
	private String bootstrapServerUrl = BoostrapServerURL + "/robot";
	private static String uri = "http://localhost:8080/server";

	@Autowired
	RobotRepository robotRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	private RobotAttributeObjectRepository robotAttributeObjectRepository;

	@Autowired
	private RobotControllerObjectRepository robotControllerObjectRepository;

	@RequestMapping("/test")
	public String index() {
		return "Greeting from Spring Boot!";
	}

	@RequestMapping(value = "/bootstrap/{id}", method = RequestMethod.GET)
	public String bootstrap(@PathVariable(value = "id") String id) {
		Robot robot = robotRepository.findByrobotId(id);
		String message;
		if (robot == null) {
			message = "Robot with robotId " + id + " does not exist!";
			return message;
		}
		if (robot.isBoostrapStatus()) {
			message = "Robot with robotId " + id + " already bootstrap. Pleaes register!";
			return message;
		}
		Device device = new Device();
		device.setEndpointClientName(robot.getRobotId() + robot.getManufacturer());
		device.setSMSNumber(robot.getModelNumber());
		device.setObjectsAndObjectInstances("</0/0>, </1>, </2/0>, </3/0>, </10>, </11>");
		device.setLWM2MVersion(robot.getSerialNumber());
		device.setLifetime(86400);
		device.setBindingMode("U");

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(bootstrapServerUrl, device, String.class);

		if (response == null) {
			message = "Bootstrap failed for robot " + id;
			return message;
		}
		robot.setBoostrapStatus(true);
		robot.setServerURL(response);
		robotRepository.save(robot);
		return "Bootstrap succeed for robot " + id;
	}

	@RequestMapping(value = "/getInfo/{id}", method = RequestMethod.GET)
	public String getRegisterInfomation(@PathVariable(value = "id") String id) {
		Robot robot = robotRepository.findByrobotId(id);
		if (robot == null) {
			return "Cannot find this client with id: " + id;
		}
		return robot.toString();
	}

	@RequestMapping(value = "/register/{id}")
	public String register(@PathVariable(value = "id") String id) {
		Robot robot = robotRepository.findByrobotId(id);
		String message;
		if (robot == null) {
			message = "Robot with robotId " + id + " does not exist!";
			return message;
		}
		if (!robot.isBoostrapStatus()) {
			message = "Robot with robotId " + id + " do not bootstrap. Pleaes bootsrap first!";
			return message;
		}
		if (robot.isRegisterStatus()) {
			message = "Robot with robotId " + id + " already register.";
			return message;
		}
		Device device = new Device();
		device.setEndpointClientName(robot.getRobotId() + robot.getManufacturer());
		device.setSMSNumber(robot.getModelNumber());
		device.setObjectsAndObjectInstances("</0/0>, </1>, </2/0>, </3/0>, </10>, </11>");
		device.setLWM2MVersion(robot.getSerialNumber());
		device.setLifetime(86400);
		device.setBindingMode("U");

		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Sending the request: " + device);
		String url = robot.getServerURL() + "/register";
		restTemplate.put(url, device);

		System.out.println("Receiving the response: 200 (OK)");

		robot.setRegisterStatus(true);
		robotRepository.save(robot);
		return "Register successfully.";
	}

	@RequestMapping(value = "/update/{id}")
	public String update(@PathVariable(value = "id") String id) {
		Robot robot = robotRepository.findByrobotId(id);
		String message;
		if (robot == null) {
			message = "Robot with robotId " + id + " does not exist!";
			return message;
		}
		if (!robot.isBoostrapStatus()) {
			message = "Robot with robotId " + id + " do not bootstrap. Pleaes bootsrap first!";
			return message;
		}
		if (!robot.isRegisterStatus()) {
			message = "Robot with robotId " + id + " do not register. Please register first!";
			return message;
		}
		Device device = new Device();
		device.setEndpointClientName(robot.getRobotId() + robot.getManufacturer());
		device.setSMSNumber(robot.getModelNumber());
		device.setObjectsAndObjectInstances("</1/102>, </2/0>, </3/0>, </4/0>, </5>");
		device.setLWM2MVersion(robot.getSerialNumber());
		device.setLifetime(86400);
		device.setBindingMode("U");
		RestTemplate restTemplate = new RestTemplate();

		System.out.println("Sending the request: " + device);
		String url = robot.getServerURL() + "/update";

		String update = restTemplate.postForObject(url, device, String.class);

		System.out.println("Receiving the response: 200 (OK)");
		return "Update successfully.";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deRegister(@PathVariable(value = "id") String id) {
		Robot robot = robotRepository.findByrobotId(id);
		String message;
		if (robot == null) {
			message = "Robot with robotId " + id + " does not exist!";
			return message;
		}
		if (!robot.isBoostrapStatus()) {
			message = "Robot with robotId " + id + " do not bootstrap. Pleaes bootsrap first!";
			return message;
		}
		if (!robot.isRegisterStatus()) {
			message = "Robot with robotId " + id + " do not register. Please register first!";
			return message;
		}
		Map<String, String> params = new HashMap<>();
		params.put("endpointClientName", robot.getRobotId() + robot.getManufacturer());

		RestTemplate restTemplate = new RestTemplate();
		String url = robot.getServerURL() + "/delete" + "/{endpointClientName}";
		System.out.println("Sending the request: de register device!");
		restTemplate.delete(url, params);

		System.out.println("Receiving the response: 200 (OK)");

		robot.setBoostrapStatus(false);
		robot.setRegisterStatus(false);
		robot.setServerURL(null);
		robotRepository.save(robot);
		return "De-Register successfully.";
	}

	// read
	@RequestMapping(value = "/value/{objectId}/{objectInstanceId}/{resourceId}", method = RequestMethod.GET)
	private Menu read(@PathVariable("objectId") int objectId, @PathVariable("objectInstanceId") int objectInstanceId,
			@PathVariable("resourceId") int resourceId) {
		Menu menu = null;
		System.out.println("Receive Read Message: " + " objectId = " + objectId + " objectInstanceId = "
				+ objectInstanceId + " resourceId = " + resourceId);
		for (RobotControllerObject object : robotControllerObjectRepository.findAll()) {
			if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
				for (String menuId : object.getMenuId()) {
					if (Integer.valueOf(menuId) == resourceId)
						return menuRepository.findByCourseId(menuId);
				}
			}
		}
		return menu;
	}

	// write
	@RequestMapping(value = "/value/{objectId}/{objectInstanceId}/{resourceId}", method = RequestMethod.POST)
	private ResponseEntity<String> write(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId,
			@RequestBody Menu menu) {
		System.out.println("Receive Write Message: " + " objectId = " + objectId + " objectInstanceId = "
				+ objectInstanceId + " resourceId = " + resourceId);
		for (RobotControllerObject object : robotControllerObjectRepository.findAll()) {
			if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
				for (String menuId : object.getMenuId()) {
					if (menuId.equals(menu.getCourseId())) {
						Menu menuInDB = menuRepository.findByCourseId(menuId);
						menuRepository.delete(menuInDB);
						menuRepository.save(menu);
						break;
					}
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// discover
	@RequestMapping(value = "/attribute/{objectId}/{objectInstanceId}/{resourceId}", method = RequestMethod.GET)
	private LWM2MAttribute discover(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {
		LWM2MAttribute attribute = null;
		System.out.println("Receive Discover Message: " + " objectId = " + objectId + " objectInstanceId = "
				+ objectInstanceId + " resourceId = " + resourceId);
		for (RobotAttributeObject object : robotAttributeObjectRepository.findAll()) {
			if (object.getObjectId() == objectId && object.getObjectInstanceId() == objectInstanceId
					&& object.getResourceId() == resourceId) {
				attribute = new LWM2MAttribute();
				attribute.setSt(object.getAttribute().getSt());
				System.out.println("find the record in database");
				break;
			}
		}
		System.out.println("Send Message: 200 (OK)");
		return attribute;
	}

	// writeAttributes
	@RequestMapping(value = "/attribute/{objectId}/{objectInstanceId}/{resourceId}", method = RequestMethod.PUT)
	private ResponseEntity<String> writeAttributes(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId,
			@RequestBody LWM2MAttribute attribute) {
		RobotAttributeObject attributeObject = null;

		System.out.println("Receive WriteAttributes Message: " + " objectId = " + objectId + " objectInstanceId = "
				+ objectInstanceId + " resourceId = " + resourceId);

		System.out.println(attribute);

		for (RobotAttributeObject object : robotAttributeObjectRepository.findAll()) {
			if (object.getObjectId() == objectId && object.getObjectInstanceId() == objectInstanceId
					&& object.getResourceId() == resourceId) {
				attributeObject = object;
				// System.out.println("find the record in database");
				break;
			}
		}
		if (attributeObject == null) {
			attributeObject = new RobotAttributeObject();
			attributeObject.setAttribute(new LWM2MAttribute());
		}

		attributeObject.setObjectId(objectId);
		attributeObject.setObjectInstanceId(objectInstanceId);
		attributeObject.setResourceId(resourceId);

		attributeObject.getAttribute().setSt(attribute.getSt());

		// save to database
		robotAttributeObjectRepository.save(attributeObject);

		// set observation flag in TVController
		if ("stop".equals(attribute.getCancel())) {
			RobotController.setObserved(false);
		}

		System.out.println("Send Message: 200 (OK)");

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// execute
	@RequestMapping(value = "/attribute/{objectId}/{objectInstanceId}/{resourceId}/{arg}", method = RequestMethod.GET)
	private ResponseEntity<String> execute(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId,
			@PathVariable("arg") int arg) {
		System.out.println("Receive Write Message: " + " objectId = " + objectId + " objectInstanceId = "
				+ objectInstanceId + " resourceId = " + resourceId);
		for (RobotControllerObject object : robotControllerObjectRepository.findAll()) {
			if (object.getThisObjectID() == objectId && object.getThisObjectInstanceID() == objectInstanceId) {
				for (String menuId : object.getMenuId()) {
					if (Integer.valueOf(menuId) == resourceId) {
						Menu menu = menuRepository.findByCourseId(menuId);
						menu.execute(arg);
					}
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// create
	@RequestMapping(value = "/object/{objectId}/{objectInstanceId}", method = RequestMethod.POST)
	private ResponseEntity<String> create(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId) {
		System.out.println(
				"Receive Create Message: " + " objectId = " + objectId + " objectInstanceId = " + objectInstanceId);

		switch (RobotObjectID.fromInt(objectId)) {
		case CONTROL_OBJECT_ID:

			RobotControllerObject robotControlObject = new RobotControllerObject();
			// default value
			robotControlObject.setThisObjectInstanceID(objectInstanceId);
			String[] menuId = { "1", "2" };
			robotControlObject.setMenuId(menuId);

			robotControllerObjectRepository.save(robotControlObject);

			break;

		default:
			System.out.println("Send Message: 20X (NOT FOUND)");

			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		System.out.println("Send Message: 200 (OK)");
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	// delete
	@RequestMapping(value = "/object/{objectId}/{objectInstanceId}", method = RequestMethod.DELETE)
	private ResponseEntity<String> delete(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId) {
		System.out.println(
				"Receive Delete Message: " + " objectId = " + objectId + " objectInstanceId = " + objectInstanceId);

		switch (RobotObjectID.fromInt(objectId)) {
		case CONTROL_OBJECT_ID:
			for (RobotControllerObject object : robotControllerObjectRepository.findAll()) {
				if (object.getThisObjectInstanceID() == objectInstanceId) {
					robotControllerObjectRepository.delete(object);
				}
			}
			break;
		default:
			System.out.println("Send Message: 20X (NOT FOUND)");

			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

		}

		System.out.println("Send Message: 200 (OK)");

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// observe
	@RequestMapping(value = "/observe/{objectId}/{objectInstanceId}/{resourceId}", method = RequestMethod.GET)
	private ResponseEntity<String> observe(@PathVariable("objectId") int objectId,
			@PathVariable("objectInstanceId") int objectInstanceId, @PathVariable("resourceId") int resourceId) {
		System.out.println("Received Message: " + " objectId = " + objectId + " objectInstanceId = " + objectInstanceId
				+ " resourceId" + resourceId);

		System.out.println("Replied Message: 200 (OK)");

		System.out.println("Object is set to be observed: /" + objectId + "/" + objectInstanceId + "/" + resourceId);

		observed = true;
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	public static boolean isObserved() {
		return observed;
	}

	public static void setObserved(boolean observed) {
		RobotController.observed = observed;
	}

	public static void notifyServer(String change) {
		RestTemplate restTemplate = new RestTemplate();
		if (!RobotController.isObserved()) {
			return;
		}
		uri += "/notify";
		String response = restTemplate.postForObject(uri, change, String.class);
		if (response.equalsIgnoreCase("cancel")) {
			setObserved(false);
		}
		System.out.println("Client recieve response: " + response + " from server.");
	}

	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public String addMenu(@RequestBody Menu request) {
		menuRepository.save(request);
		notifyServer("Create new Menu: " + request.getCourseId());

		return "Create success";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteMenu(@PathVariable(value = "id") String id) {
		Menu menu = menuRepository.findByCourseId(id);
		menuRepository.delete(menu);
		notifyServer("Delete Menu: " + menu.getCourseId());
		return "Delete success";
	}
}
