package org.usfirst.frc.team449.robot.mechanism.catapult;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team449.robot.MappedSubsystem;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class Catapult extends MappedSubsystem {

	private DoubleSolenoid solenoid;

	public int timeUp;

	public Catapult(maps.org.usfirst.frc.team449.robot.mechanism.catapult.CatapultMap.Catapult map){
		super(map.getMechanism());
		solenoid = new DoubleSolenoid(15,map.getSolenoid().getForward(), map.getSolenoid().getReverse());
		timeUp = map.getTimeUp();
	}

	/**
	 * Toggle the catapult solenoid.
	 * @param val True if solenoid should be on, false otherwise.
	 */
	public void setFired(DoubleSolenoid.Value val){
		solenoid.set(val);
	}

	public DoubleSolenoid.Value isFired(){
		return solenoid.get();
	}

	protected void initDefaultCommand(){
		//Nothing
	}
}
