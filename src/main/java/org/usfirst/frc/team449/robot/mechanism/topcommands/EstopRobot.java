package org.usfirst.frc.team449.robot.mechanism.topcommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team449.robot.ReferencingCommand;

/**
 * Created by Noah Gleason on 4/20/2017.
 */
public class EstopRobot extends Command {
	public EstopRobot(){
		super();
	}

	protected void initialize(){
		System.exit(1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
