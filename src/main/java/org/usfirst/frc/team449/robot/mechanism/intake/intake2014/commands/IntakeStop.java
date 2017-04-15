package org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class IntakeStop extends ReferencingCommand {

	public IntakeStop(Intake2014 intake){
		super(intake);
		requires(intake);
	}

	protected void initialize(){
		((Intake2014) subsystem).stopIntake();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
