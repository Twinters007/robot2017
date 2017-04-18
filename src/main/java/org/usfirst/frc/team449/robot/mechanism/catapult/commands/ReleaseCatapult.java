package org.usfirst.frc.team449.robot.mechanism.catapult.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.catapult.Catapult;

/**
 * Created by Noah Gleason on 4/18/2017.
 */
public class ReleaseCatapult extends ReferencingCommand {

	Catapult catapult;

	public ReleaseCatapult(Catapult catapult){
		super(catapult);
		this.catapult = catapult;
		requires(catapult);
	}

	@Override
	protected void initialize() {
		catapult.setFired(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
