package org.usfirst.frc.team449.robot.interfaces.oi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A tank-style dual joystick OI.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "@class")
public abstract class TankOI implements UnidirectionalOI {

	/**
	 * Get the throttle for the left side of the drive.
	 *
	 * @return percent of max speed for left motor cluster from [-1.0, 1.0]
	 */
	public abstract double getLeftThrottle();

	/**
	 * Get the throttle for the right side of the drive.
	 *
	 * @return percent of max speed for right motor cluster from [-1.0, 1.0]
	 */
	public abstract double getRightThrottle();

	/**
	 * The output to be given to the left side of the drive.
	 *
	 * @return Output to left side from [-1, 1]
	 */
	public double getLeftOutput() {
		return getLeftThrottle();
	}

	/**
	 * The output to be given to the right side of the drive.
	 *
	 * @return Output to right side from [-1, 1]
	 */
	public double getRightOutput() {
		return getRightThrottle();
	}
}
