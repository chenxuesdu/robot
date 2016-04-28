package com.robot.server.services;
/**
 * 
 * @author XueChen
 *
 */
public class PrintMenu {
	private static String stars = new String(new char[50]).replace("\0", "-");
	private static String welcome = "          Welcome to Register Server         ";

	static public void menu() {

		System.out.println(stars);
		System.out.printf("|%48s|\n", welcome);
		System.out.println(stars);
		System.out.printf("|%-48s|\n", " 2: Device Management & Service Enablement");
		System.out.printf("|%-48s|\n", "    2.1: Read");
		System.out.printf("|%-48s|\n", "    2.2: Write");
		System.out.printf("|%-48s|\n", "    2.3: Discover");
		System.out.printf("|%-48s|\n", "    2.4: Write Attributes");
		System.out.printf("|%-48s|\n", "    2.5: Execute");
		System.out.printf("|%-48s|\n", "    2.6: Create");
		System.out.printf("|%-48s|\n", "    2.7: Delete");
		System.out.printf("|%-48s|\n", " 3: Information Reporting");
		System.out.printf("|%-48s|\n", "    3.1: Observe");
		System.out.printf("|%-48s|\n", "    3.2: Cancel Observation by response");
		System.out.printf("|%-48s|\n", "    3.3: Cancel Observation by WriteAttributes ");
		System.out.printf("|%-48s|\n", " 4: Tools");
		System.out.printf("|%-48s|\n", "    4.1: Delete Registered Devices");
		System.out.printf("|%-48s|\n", "    4.2: Show Registered Devices");
		System.out.printf("|%-48s|\n", "    4.3: Show Observation Data");
		System.out.println(stars);
		System.out.println("Please input here:");
	}

}
