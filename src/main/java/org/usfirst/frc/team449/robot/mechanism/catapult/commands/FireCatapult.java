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
		timeUp = catapult.timeUp;
	}

	protected void initialize(){
		timeFired = System.currentTimeMillis();
		((Catapult)subsystem).setFired(true);
	}

	protected void execute(){
		if(System.currentTimeMillis()-timeFired>timeUp && ((Catapult)subsystem).isFired()){
			((Catapult)subsystem).setFired(false);
		}
	}

	protected void end(){
		((Catapult) subsystem).setFired(false); //Make sure shooter is down
	}

	protected boolean isFinished(){
		return true;
	}

}
