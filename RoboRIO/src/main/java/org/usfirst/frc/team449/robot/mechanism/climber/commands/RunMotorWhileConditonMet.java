package org.usfirst.frc.team449.robot.mechanism.climber.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team449.robot.interfaces.subsystem.binaryMotor.BinaryMotorSubsystem;
import org.usfirst.frc.team449.robot.interfaces.subsystem.conditional.ConditionalSubsystem;
import org.usfirst.frc.team449.robot.util.Logger;

/**
 * Run a BinaryMotor while a condition is true.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.StringIdGenerator.class)
public class RunMotorWhileConditonMet extends Command {

	/**
	 * The subsystem to execute this command on
	 */
	private BinaryMotorSubsystem subsystem;

	/**
	 * Default constructor
	 *
	 * @param subsystem The subsystem to execute this command on.
	 */
	@JsonCreator
	public <T extends Subsystem & BinaryMotorSubsystem & ConditionalSubsystem> RunMotorWhileConditonMet(@JsonProperty(required = true) T subsystem) {
		requires(subsystem);
		this.subsystem = subsystem;
		Logger.addEvent("RunMotorWhileConditonMet constructed", this.getClass());
	}

	/**
	 * Log when this command is initialized
	 */
	@Override
	protected void initialize() {
		Logger.addEvent("RunMotorWhileConditonMet init", this.getClass());
	}

	/**
	 * Run the motor
	 */
	@Override
	protected void execute() {
		subsystem.turnMotorOn();
	}

	/**
	 * Stop when the condition is met.
	 *
	 * @return true if the condition is met, false otherwise.
	 */
	@Override
	protected boolean isFinished() {
		return ((ConditionalSubsystem) subsystem).isConditionTrue();
	}

	/**
	 * Stop the motor and log that the command has ended.
	 */
	@Override
	protected void end() {
		//Stop the motor when we meet the condition.
		subsystem.turnMotorOff();
		Logger.addEvent("RunMotorWhileConditonMet end", this.getClass());
	}

	/**
	 * Stop the motor and log that the command has been interrupted.
	 */
	@Override
	protected void interrupted() {
		//Stop the motor if this command is interrupted.
		subsystem.turnMotorOff();
		Logger.addEvent("RunMotorWhileConditonMet interrupted, stopping climb.", this.getClass());
	}

}
