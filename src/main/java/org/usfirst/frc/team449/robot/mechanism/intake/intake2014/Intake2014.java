package org.usfirst.frc.team449.robot.mechanism.intake.intake2014;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team449.robot.MappedSubsystem;

/**
 * Created by Noah Gleason on 4/15/2017.
 */
public class Intake2014 extends MappedSubsystem {

	public static final int MOTOR_IN = -1;
	public static final int MOTOR_OUT = 1;

	private VictorSP motor;
	private DoubleSolenoid solenoid;

	public Intake2014(maps.org.usfirst.frc.team449.robot.mechanism.intake2014.Intake2014Map.Intake2014 map){
		super(map.getMechanism());

		motor = new VictorSP(map.getMotor().getPort());
		motor.setInverted(map.getMotor().getInverted());

		solenoid = new DoubleSolenoid(map.getPistonModuleNum(),map.getSolenoid().getForward(), map.getSolenoid().getReverse());
	}

	public void intakeSpinIn(){
		motor.set(MOTOR_IN);
	}

	public void intakeSpinOut(){
		motor.set(MOTOR_OUT);
	}

	public void intakeUp(){
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void intakeDown(){
		solenoid.set(DoubleSolenoid.Value.kForward);
	}

	public boolean isUp(){
		return solenoid.get()== DoubleSolenoid.Value.kReverse;
	}

	public double motorSetting(){
		return motor.get();
	}

	public void stopIntake(){
		motor.set(0);
	}

	@Override
	protected void initDefaultCommand(){
		//Nothing
	}

}
