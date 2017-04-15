package org.usfirst.frc.team449.robot.mechanism.catapult;

import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team449.robot.MappedSubsystem;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class Catapult extends MappedSubsystem {

	private Solenoid solenoid;

	public int timeUp;

	public Catapult(maps.org.usfirst.frc.team449.robot.mechanism.catapult.CatapultMap.Catapult map){
		super(map.getMechanism());
		solenoid = new Solenoid(15,map.getSolenoid().getPort());
		timeUp = map.getTimeUp();
	}

	/**
	 * Toggle the catapult solenoid.
	 * @param val True if solenoid should be on, false otherwise.
	 */
	public void setFired(boolean val){
		solenoid.set(val);
	}

	public boolean isFired(){
		return solenoid.get();
	}

	protected void initDefaultCommand(){
		//Nothing
	}
}
