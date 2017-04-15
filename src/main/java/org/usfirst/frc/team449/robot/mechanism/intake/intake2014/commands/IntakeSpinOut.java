package org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class IntakeSpinOut extends ReferencingCommand {

	public IntakeSpinOut(Intake2014 intake){
		super(intake);
		requires(intake);
	}

	protected void initialize(){
		((Intake2014) subsystem).intakeSpinOut();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
