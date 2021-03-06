package org.usfirst.frc.team449.robot.interfaces.subsystem.Shooter.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.jetbrains.annotations.NotNull;
import org.usfirst.frc.team449.robot.interfaces.subsystem.Shooter.ShooterSubsystem;
import org.usfirst.frc.team449.robot.util.Logger;
import org.usfirst.frc.team449.robot.util.YamlCommandWrapper;

/**
 * Turn on the shooter but not the feeder in order to give the shooter time to get up to speed.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class SpinUpShooter extends YamlCommandWrapper {

	/**
	 * The subsystem to execute this command on.
	 */
	@NotNull
	private final ShooterSubsystem subsystem;

	/**
	 * Default constructor
	 *
	 * @param subsystem The subsystem to execute this command on.
	 */
	@JsonCreator
	public SpinUpShooter(@NotNull @JsonProperty(required = true) ShooterSubsystem subsystem) {
		this.subsystem = subsystem;
	}

	/**
	 * Log when this command is initialized
	 */
	@Override
	protected void initialize() {
		Logger.addEvent("SpinUpShooter init.", this.getClass());
	}

	/**
	 * Turn the feeder off and the shooter on.
	 */
	@Override
	protected void execute() {
		subsystem.turnFeederOff();
		subsystem.turnShooterOn();
		subsystem.setShooterState(ShooterSubsystem.ShooterState.SPINNING_UP);
	}

	/**
	 * Finish immediately because this is a state-change command.
	 *
	 * @return true
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * Log when this command ends
	 */
	@Override
	protected void end() {
		Logger.addEvent("SpinUpShooter end.", this.getClass());
	}

	/**
	 * Log when this command is interrupted.
	 */
	@Override
	protected void interrupted() {
		Logger.addEvent("SpinUpShooter Interrupted!", this.getClass());
	}
}