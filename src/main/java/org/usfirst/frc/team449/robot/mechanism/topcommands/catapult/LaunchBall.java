package org.usfirst.frc.team449.robot.mechanism.topcommands.catapult;

import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.catapult.Catapult;
import org.usfirst.frc.team449.robot.mechanism.catapult.commands.FireCatapult;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.Intake2014;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands.IntakeDown;
import org.usfirst.frc.team449.robot.mechanism.intake.intake2014.commands.IntakeStop;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class LaunchBall extends ReferencingCommandGroup {

	public LaunchBall(Catapult catapult, Intake2014 intake){
		super(catapult);
		addSequential(new IntakeDown(intake));
		addSequential(new IntakeStop(intake));
		addSequential(new FireCatapult(catapult));
	}

}
