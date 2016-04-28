package com.robot.common;
/**
 * 
 * @author XueChen
 *
 */
public enum RobotObjectID {
	SECURITY_OBJECT_ID(0),
    SERVER_OBJECT_ID(1),
    ACCESS_CONTROL_OBJECT_ID(2),
    DEVICE_OBJECT_ID(3),
    FUNCTION_OBJECT_ID(10),
    CONTROL_OBJECT_ID(11);

    private final int id;

    RobotObjectID(int id) { this.id = id; }

    public int getValue() { return id; }

    public static RobotObjectID fromInt(int x) {
        switch(x) {
            case 0:
                return SECURITY_OBJECT_ID;
            case 1:
                return SERVER_OBJECT_ID;
            case 2:
                return ACCESS_CONTROL_OBJECT_ID;
            case 3:
                return DEVICE_OBJECT_ID;
            case 10:
                return FUNCTION_OBJECT_ID;
            case 11:
                return CONTROL_OBJECT_ID;
        }
        return null;
    }

}
