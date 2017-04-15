package org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class ToggleUpDown extends ReferencingCommand {

	private Intake2014 intake;

	public ToggleUpDown(Intake2014 intake){
		super(intake);
		requires(intake);
		this.intake = intake;
	}

	protected void initialize(){
		if(intake.isUp()){
			intake.intakeDown();
		} else {
			intake.intakeUp();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
