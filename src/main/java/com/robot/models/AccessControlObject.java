package com.robot.models;
/**
 * 
 * @author XueChen
 *
 */
public class AccessControlObject {
	private String id; // Used by MongoDB

	private final int thisObjectID = 2; // device = 2
	private int thisObjectInstanceID; // Multiple = 0...n

	private int ObjectID;
	private int ObjectInstanceID;
	private int ACL;
	private int AccessControlOwner;

	public AccessControlObject() {
	}

	public AccessControlObject(int objectID, int objectInstanceID, int ACL, int accessControlOwner) {
		ObjectID = objectID;
		ObjectInstanceID = objectInstanceID;
		this.ACL = ACL;
		AccessControlOwner = accessControlOwner;
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

	public void setThisObjectInstanceID(int thisObjectInstanceID) {
		this.thisObjectInstanceID = thisObjectInstanceID;
	}

	public int getObjectID() {
		return ObjectID;
	}

	public void setObjectID(int objectID) {
		ObjectID = objectID;
	}

	public int getObjectInstanceID() {
		return ObjectInstanceID;
	}

	public void setObjectInstanceID(int objectInstanceID) {
		ObjectInstanceID = objectInstanceID;
	}

	public int getACL() {
		return ACL;
	}

	public void setACL(int ACL) {
		this.ACL = ACL;
	}

	public int getAccessControlOwner() {
		return AccessControlOwner;
	}

	public void setAccessControlOwner(int accessControlOwner) {
		AccessControlOwner = accessControlOwner;
	}

	@Override
	public String toString() {
		return "AccessControlObject{" + "id='" + id + '\'' + ", thisObjectID=" + thisObjectID
				+ ", thisObjectInstanceID=" + thisObjectInstanceID + ", ObjectID=" + ObjectID + ", ObjectInstanceID="
				+ ObjectInstanceID + ", ACL=" + ACL + ", AccessControlOwner=" + AccessControlOwner + '}';
	}
}
