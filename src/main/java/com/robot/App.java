package com.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.robot.common.RobotObjectID;
import com.robot.server.ServerController;
import com.robot.server.repositories.IoTClientRepository;
import com.robot.server.services.Operations;
import com.robot.server.services.PrintMenu;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	
	@Autowired
	private IoTClientRepository ioTClientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PrintMenu.menu();
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String s = bufferRead.readLine();

			switch (s) {

			case "1":
				System.out.println("Nothing to do.");

				break;

			case "2":
			case "2.1":
				Operations req21 = new Operations("http://localhost:8080/value",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 1);
				req21.read();
				break;

			case "2.2":
				Operations req23 = new Operations("http://localhost:8080/value",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 1);
				req23.write();
				break;

			case "2.3":
				Operations req1 = new Operations("http://localhost:8080/attribute",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 2);
				req1.discover();
				break;

			case "2.4":
				Operations req2 = new Operations("http://localhost:8080/attribute",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 2);
				req2.writeAttribute(2);
				break;

			case "2.5":
				Operations req25 = new Operations("http://localhost:8080/attribute",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 2);
				req25.execute();
				break;

			case "2.6":
				Operations reqCreate = new Operations("http://localhost:8080/object",
						RobotObjectID.CONTROL_OBJECT_ID, 1);
				reqCreate.create();
				break;

			case "2.7":
				Operations reqDelete = new Operations("http://localhost:8080/object",
						RobotObjectID.CONTROL_OBJECT_ID, 1);
				reqDelete.delete();
				break;

			case "3":
			case "3.1":
				Operations observeRequest = new Operations("http://localhost:8080/observe");
				observeRequest.observe();
				ServerController.setCancelObserve(false);
				System.out.println("Observation is set, and client is going to notify its data.");
				break;

			case "3.2":
				ServerController.setCancelObserve(true);
				System.out.println("Observation is canceled, and will inform client in the coming notify message.");
				break;

			case "3.3":
				Operations cancelObserveRequest = new Operations("http://localhost:8080/attribute",
						RobotObjectID.CONTROL_OBJECT_ID, 0, 2);
				cancelObserveRequest.writeAttribute("stop");
				break;

			case "4":
			case "4.1":
				ioTClientRepository.deleteAll();
				System.out.println("All the registered devices have been removed from database.");

				break;

			default:
				System.out.println("Please input number(s) 1.1 1.2 2.1 2.2 2.3 2.4 2.5 2.6 2.7 3.1 3.2 3.3\n\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
