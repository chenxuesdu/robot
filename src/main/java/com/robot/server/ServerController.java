package com.robot.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.robot.common.RobotObjectID;
import com.robot.models.Device;
import com.robot.server.repositories.IoTClientRepository;
import com.robot.server.services.Operations;

@RestController
@RequestMapping("/server")
public class ServerController {

	private static boolean cancelObserve = false; // true: cancel, false: not
													// cancel

	@Autowired
	private IoTClientRepository ioTClientRepository;

	public static boolean isCancelObserve() {
		return cancelObserve;
	}

	public static void setCancelObserve(boolean cancelObserve) {
		ServerController.cancelObserve = cancelObserve;
	}

	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	private void registerRobot(@RequestBody Device request) {
		System.out.println("Received Message: " + request);
		IoTClient ioTClient = ioTClientRepository.findByEndpointClientName(request.getEndpointClientName());
		if (ioTClient != null) {
			// the LWM2M Server removes the existing registration information
			// and performs the new “Register” operation
			ioTClientRepository.delete(request.getEndpointClientName());
		} else {
			ioTClient = new IoTClient();
		}
		ioTClient.setEndpointClientName(request.getEndpointClientName());
		ioTClient.setLifetime(request.getLifetime());
		ioTClient.setBindingMode((request.getBindingMode()));
		ioTClient.setLWM2MVersion(request.getLWM2MVersion());
		ioTClient.setObjectsAndObjectInstances(request.getObjectsAndObjectInstances());
		ioTClient.setSMSNumber(request.getSMSNumber());
		ioTClientRepository.save(ioTClient);
		System.out.println("Replied Message: 200 (OK)");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private void updateRobot(@RequestBody Device update) {

		System.out.println("Received Message: " + update);

		IoTClient ioTClient = ioTClientRepository.findByEndpointClientName(update.getEndpointClientName());

		if (ioTClient == null) {
			System.out.println("Can't find the registered device in database.");
		}

		// delete the previouse data
		ioTClientRepository.delete(update.getEndpointClientName());

		// Update the data
		ioTClient.setLifetime(update.getLifetime());
		ioTClient.setBindingMode((update.getBindingMode()));
		ioTClient.setObjectsAndObjectInstances(update.getObjectsAndObjectInstances());
		ioTClient.setSMSNumber(update.getSMSNumber());

		// save data into database
		ioTClientRepository.save(ioTClient);
		System.out.println("Replied Message: 200 (OK)");
	}

	@RequestMapping(value = "/delete/{endpointClientName}", method = RequestMethod.DELETE)
	private void deleteRobot(@PathVariable("endpointClientName") String endpointClientName) {

		System.out.printf("Received Message: De-Register Device: %s\n", endpointClientName);

		ioTClientRepository.delete(endpointClientName);

		System.out.println("Replied Message: 200 (OK)");

	}

	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public String report(@RequestBody String change) {
		System.out.println("Change is " + change);
		if (isCancelObserve()) {
			return "cancel";
		}
		return "get change success";
	}

	@RequestMapping(value = "cancelObserver", method = RequestMethod.GET)
	public String cancelObserv() {
		Operations cancelObserveRequest = new Operations("http://localhost:8080/attribute",
				RobotObjectID.CONTROL_OBJECT_ID, 0, 2);
		cancelObserveRequest.writeAttribute("stop");
		return "cancel Observation!";
	}
}
