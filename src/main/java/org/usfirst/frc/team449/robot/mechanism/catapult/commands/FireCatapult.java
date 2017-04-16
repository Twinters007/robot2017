package org.usfirst.frc.team449.robot.mechanism.catapult.commands;

import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.catapult.Catapult;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class FireCatapult extends ReferencingCommand {

	private long timeFired;
	private int timeUp;

	public FireCatapult(Catapult catapult){
		super(catapult);
		requires(catapult);
	}

	protected void initialize(){
		((Catapult)subsystem).setFired(true);
	}

	protected void execute(){
	}

	protected void end(){
		((Catapult) subsystem).setFired(false); //Make sure shooter is down
	}

	protected boolean isFinished(){
		return true;
	}

}
