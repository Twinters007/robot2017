package org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import maps.org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class IntakeSpinIn extends ReferencingCommand {

	public IntakeSpinIn(Intake2014 intake){
		super(intake);
		requires(intake);
	}

	protected void initialize(){
		SmartDashboard.putString("Intake state","in");
		((Intake2014) subsystem).intakeSpinIn();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
