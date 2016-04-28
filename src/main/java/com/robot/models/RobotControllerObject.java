package com.robot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.robot.common.RobotObjectID;

@Document(collection="robotcontrollerobject")
public class RobotControllerObject {
	@Id
	private String id; // Used by MongoDB

	private final int thisObjectID = RobotObjectID.CONTROL_OBJECT_ID.getValue(); // device
																					// =
																					// 11
	private int thisObjectInstanceID = 0; // Single = 0

	private int lock; // resource id = 0 Execute resource: Lock the robot (in this
						// project locked status means no operation on console)
	//private int volumeOfVoice; // [0...49] resource id = 1
	private String[] menuId; // [0...499] resource id = 2

	public RobotControllerObject() {
    }

	public RobotControllerObject(int lock, String[] menuId) {
        this.lock = lock;
        this.menuId = menuId;
    }

	public void setThisObjectInstanceID(int thisObjectInstanceID) {
		this.thisObjectInstanceID = thisObjectInstanceID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getThisObjectID() {
		return thisObjectID;
	}

	public int getThisObjectInstanceID() {
		return thisObjectInstanceID;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public String[] getMenuId() {
		return menuId;
	}

	public void setMenuId(String[] menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RobotControlObject{" + "id='" + id + '\'' + ", thisObjectID=" + thisObjectID + ", thisObjectInstanceID="
				+ thisObjectInstanceID + ", lock=" + lock + ", menuId="
				+ menuId + '}';
	}
}
