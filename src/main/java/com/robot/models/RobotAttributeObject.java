package com.robot.models;

import com.robot.common.LWM2MAttribute;
/**
 * 
 * @author XueChen
 *
 */
public class RobotAttributeObject {
	private String id;
	private int objectId;
	private int objectInstanceId;
	private int resourceId;
	private LWM2MAttribute attribute;

	public RobotAttributeObject() {
    }

	public RobotAttributeObject(String id, int objectId, int objectInstanceId, int resourceId, LWM2MAttribute attribute) {
        this.id = id;
        this.objectId = objectId;
        this.objectInstanceId = objectInstanceId;
        this.resourceId = resourceId;
        this.attribute = attribute;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getObjectInstanceId() {
		return objectInstanceId;
	}

	public void setObjectInstanceId(int objectInstanceId) {
		this.objectInstanceId = objectInstanceId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public LWM2MAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(LWM2MAttribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return "TVAttributeObject{" + "id='" + id + '\'' + ", objectId=" + objectId + ", objectInstanceId="
				+ objectInstanceId + ", resourceId=" + resourceId + ", attribute=" + attribute + '}';
	}
}
