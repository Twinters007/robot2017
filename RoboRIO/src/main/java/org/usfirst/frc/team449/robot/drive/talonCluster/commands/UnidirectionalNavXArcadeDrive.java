package org.usfirst.frc.team449.robot.drive.talonCluster.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.components.ToleranceBufferAnglePID;
import org.usfirst.frc.team449.robot.interfaces.drive.unidirectional.UnidirectionalDrive;
import org.usfirst.frc.team449.robot.interfaces.oi.ArcadeOI;
import org.usfirst.frc.team449.robot.interfaces.subsystem.NavX.NavxSubsystem;
import org.usfirst.frc.team449.robot.interfaces.subsystem.NavX.commands.PIDAngleCommand;
import org.usfirst.frc.team449.robot.util.BufferTimer;
import org.usfirst.frc.team449.robot.util.Logger;

/**
 * Drive with arcade drive setup, and when the driver isn't turning, use a NavX to stabilize the robot's alignment.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class UnidirectionalNavXArcadeDrive extends PIDAngleCommand {
	/**
	 * The UnidirectionalDrive this command is controlling.
	 */
	protected UnidirectionalDrive driveSubsystem;

	/**
	 * The OI giving the vel and turn stick values.
	 */
	private ArcadeOI oi;

	/**
	 * Whether or not we should be using the NavX to drive straight stably.
	 */
	private boolean drivingStraight;

	/**
	 * The velocity input from OI. Should be between -1 and 1.
	 */
	private double vel;

	/**
	 * The rotation input from OI. Should be between -1 and 1.
	 */
	private double rot;

	/**
	 * The maximum velocity for the robot to be at in order to switch to driveStraight, in degrees/sec
	 */
	private double maxAngularVelToEnterLoop;

	/**
	 * A bufferTimer so we only switch to driving straight when the conditions are met for a certain period of time.
	 */
	private BufferTimer driveStraightLoopEntryTimer;

	/**
	 * Default constructor
	 *
	 * @param PID   The angle PID.
	 * @param drive The drive to execute this command on.
	 * @param oi    The OI controlling the robot.
	 */
	@JsonCreator
	public <T extends Subsystem & UnidirectionalDrive & NavxSubsystem> UnidirectionalNavXArcadeDrive(@JsonProperty(required = true) ToleranceBufferAnglePID PID,
	                                                                                                 @JsonProperty(required = true) T drive,
	                                                                                                 @JsonProperty(required = true) ArcadeOI oi) {
		//Assign stuff
		super(PID, drive);
		maxAngularVelToEnterLoop = PID.getMaxAngularVelToEnterLoop();
		this.oi = oi;
		driveSubsystem = drive;

		driveStraightLoopEntryTimer = new BufferTimer(PID.getLoopEntryDelay());

		//Needs a requires because it's a default command.
		requires(drive);

		//Logging, but in Spanish.
		Logger.addEvent("Drive Robot bueno", this.getClass());
	}

	/**
	 * Initialize PIDController and variables.
	 */
	@Override
	protected void initialize() {
		//Reset all values of the PIDController and enable it.
		this.getPIDController().reset();
		this.getPIDController().enable();
		Logger.addEvent("UnidirectionalNavXArcadeDrive init.", this.getClass());

		//Initial assignment
		drivingStraight = false;
		vel = oi.getFwd();
		rot = oi.getRot();
	}

	/**
	 * Decide whether or not we should be in free drive or straight drive, and log data.
	 */
	@Override
	protected void execute() {
		//Set vel and rot to what they should be.
		vel = oi.getFwd();
		rot = oi.getRot();

		//If we're driving straight but the driver tries to turn or overrides the NavX:
		if (drivingStraight && (rot != 0 || ((NavxSubsystem) driveSubsystem).getOverrideNavX())) {
			//Switch to free drive
			drivingStraight = false;
			Logger.addEvent("Switching to free drive.", this.getClass());
		}
		//If we're free driving and the driver lets go of the turn stick:
		else if (driveStraightLoopEntryTimer.get(!(((NavxSubsystem) driveSubsystem).getOverrideNavX()) && !(drivingStraight) &&
				rot == 0 && Math.abs(((NavxSubsystem) driveSubsystem).getNavX().getRate()) <= maxAngularVelToEnterLoop)) {
			//Switch to driving straight
			drivingStraight = true;
			//Set the setpoint to the current heading and reset the NavX
			this.getPIDController().reset();
			this.getPIDController().setSetpoint(subsystem.getGyroOutput());
			this.getPIDController().enable();
			Logger.addEvent("Switching to DriveStraight.", this.getClass());
		}

		//Log data and stuff
		SmartDashboard.putBoolean("driving straight?", drivingStraight);
		SmartDashboard.putBoolean("Override Navx", ((NavxSubsystem) driveSubsystem).getOverrideNavX());
		SmartDashboard.putNumber("Vel Axis", vel);
		SmartDashboard.putNumber("Rot axis", rot);
	}

	/**
	 * Run constantly because this is a defaultDrive
	 *
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Log when this command ends
	 */
	@Override
	protected void end() {
		Logger.addEvent("UnidirectionalNavXArcadeDrive End.", this.getClass());
	}

	/**
	 * Stop the motors and log when this command is interrupted.
	 */
	@Override
	protected void interrupted() {
		Logger.addEvent("UnidirectionalNavXArcadeDrive Interrupted! Stopping the robot.", this.getClass());
		driveSubsystem.setOutput(0.0, 0.0);
	}

	/**
	 * Give the correct output to the motors based on whether we're in free drive or drive straight.
	 *
	 * @param output The output of the angular PID loop.
	 */
	@Override
	protected void usePIDOutput(double output) {
		//If we're driving straight..
		if (drivingStraight) {
			//Process the output (minimumOutput, deadband, etc.)
			output = processPIDOutput(output);

			//Log stuff
			SmartDashboard.putNumber("PID output", output);

			//Adjust the heading according to the PID output, it'll be positive if we want to go right.
			driveSubsystem.setOutput(vel - output, vel + output);
		}
		//If we're free driving...
		else {
			//Set the throttle to normal arcade throttle.
			driveSubsystem.setOutput(oi.getLeftOutput(), oi.getRightOutput());
		}
	}
}