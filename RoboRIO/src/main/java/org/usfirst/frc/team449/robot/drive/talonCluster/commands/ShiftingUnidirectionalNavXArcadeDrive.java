package org.usfirst.frc.team449.robot.drive.talonCluster.commands;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team449.robot.components.ToleranceBufferAnglePID;
import org.usfirst.frc.team449.robot.interfaces.drive.shifting.ShiftingDrive;
import org.usfirst.frc.team449.robot.interfaces.drive.unidirectional.UnidirectionalDrive;
import org.usfirst.frc.team449.robot.interfaces.oi.ArcadeOI;
import org.usfirst.frc.team449.robot.interfaces.subsystem.NavX.NavxSubsystem;

/**
 * Drive with arcade drive setup, autoshift, and when the driver isn't turning, use a NavX to stabilize the robot's
 * alignment.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class ShiftingUnidirectionalNavXArcadeDrive extends UnidirectionalNavXArcadeDrive {
	/**
	 * Default constructor
	 *
	 * @param map   The angle PID map containing PID and other tuning constants.
	 * @param drive The drive to execute this command on.
	 * @param oi    The OI controlling the robot.
	 */
	public <T extends Subsystem & UnidirectionalDrive & NavxSubsystem & ShiftingDrive> ShiftingUnidirectionalNavXArcadeDrive(@JsonProperty(required = true) ToleranceBufferAnglePID map,
	                                                                                                                         @JsonProperty(required = true) T drive,
	                                                                                                                         @JsonProperty(required = true) ArcadeOI oi) {
		super(map, drive, oi);
	}

	/**
	 * Autoshift, decide whether or not we should be in free drive or straight drive, and log data.
	 */
	@Override
	public void execute() {
		//Auto-shifting
		((ShiftingDrive) driveSubsystem).autoshift();
		super.execute();
	}
}
