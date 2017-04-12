package org.usfirst.frc.team449.robot.nt.dashboard;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDrive;
import org.usfirst.frc.team449.robot.mechanism.activegear.ActiveGearSubsystem;
import org.usfirst.frc.team449.robot.mechanism.climber.ClimberSubsystem;
import org.usfirst.frc.team449.robot.mechanism.feeder.FeederSubsystem;
import org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.SingleFlywheelShooter;

/**
 * Created by ryant on 2017-04-11.
 */
public class DashboardManager extends Command {

	/**
	 * Gyro heading
	 */
	private double heading;
	/**
	 * Time left in the match
	 */
	private double timeLeft;

	private ClimberSubsystem climber;
	private FeederSubsystem feeder;
	private SingleFlywheelShooter shooter;
	private TalonClusterDrive drive;
	private ActiveGearSubsystem gHandler;

	private NetworkTable dashTable;

	public DashboardManager(ClimberSubsystem _climber, FeederSubsystem _feeder,
	                        SingleFlywheelShooter _shooter, TalonClusterDrive _drive, ActiveGearSubsystem _gHandler) {
		climber = _climber;
		feeder = _feeder;
		shooter = _shooter;
		drive = _drive;
		gHandler = _gHandler;

		dashTable = NetworkTable.getTable("Dashboard449");
	}

	@Override
	protected void execute() {
		dashTable.putNumber("time_left", DriverStation.getInstance().getMatchTime());
		dashTable.putNumber("heading", drive.getGyroOutput());
		dashTable.putBoolean("climber_on", climber.canTalonSRX.getPower() != 0);
		dashTable.putBoolean("feeder_on", feeder.running);
		dashTable.putBoolean("shooter_on", shooter.spinning);
		dashTable.putBoolean("highgear_on", drive.shifter.get() == DoubleSolenoid.Value.kReverse);
		dashTable.putNumber("left_vel", drive.leftMaster.getSpeed() / drive.leftMaster.getMaxSpeed());
		dashTable.putNumber("right_vel", drive.rightMaster.getSpeed() / drive.rightMaster.getMaxSpeed());
		dashTable.putBoolean("gHandler_fired", gHandler.piston.get() == DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
