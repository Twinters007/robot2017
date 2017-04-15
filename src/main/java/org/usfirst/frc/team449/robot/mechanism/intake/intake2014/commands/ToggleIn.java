package org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
@Deprecated
public class ToggleIn extends ReferencingCommand {

	private Intake2014 intake;

	public ToggleIn(Intake2014 intake){
		super(intake);
		requires(intake);
		this.intake = intake;
	}

	@Override
	protected void initialize() {
		if(intake.motorSetting()==Intake2014.MOTOR_IN){
			intake.stopIntake();
		} else {
			intake.intakeSpinIn();
		}
	}
}
