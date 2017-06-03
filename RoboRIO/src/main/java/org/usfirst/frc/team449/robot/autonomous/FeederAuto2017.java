package org.usfirst.frc.team449.robot.autonomous;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.TwoSideMPSubsystem.TwoSideMPSubsystem;
import org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.TwoSideMPSubsystem.commands.RunProfileTwoSides;
import org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.commands.RunLoadedProfile;
import org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.commands.RunProfile;
import org.usfirst.frc.team449.robot.interfaces.subsystem.solenoid.commands.SolenoidReverse;
import org.usfirst.frc.team449.robot.mechanism.activegear.ActiveGearSubsystem;
import org.usfirst.frc.team449.robot.util.MotionProfileData;

/**
 * The autonomous routine to deliver a gear to the center gear.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.StringIdGenerator.class)
public class FeederAuto2017 extends CommandGroup {

	/**
	 * Default constructor.
	 *
	 * @param drive              The drive subsystem to execute this command on. Must have the profile to drive up to
	 *                              the peg already loaded into it.
	 * @param gearHandler        The gear handler to execute this command on.
	 * @param dropGear           Whether or not to drop the gear.
	 * @param leftBackupProfile  The motion profile for the left side of the drive to execute to back up from the peg.
	 * @param rightBackupProfile The motion profile for the right side of the drive to execute to back up from the peg.
	 * @param forwardsProfile    The motion profile for both sides to drive forwards after backing up from the peg.
	 */
	@JsonCreator
	public FeederAuto2017(@JsonProperty(required = true) TwoSideMPSubsystem drive,
	                      @JsonProperty(required = true) ActiveGearSubsystem gearHandler,
	                      @JsonProperty(required = true) boolean dropGear,
	                      @JsonProperty(required = true) MotionProfileData leftBackupProfile,
	                      @JsonProperty(required = true) MotionProfileData rightBackupProfile,
	                      @JsonProperty(required = true) MotionProfileData forwardsProfile) {
		addSequential(new RunLoadedProfile(drive, 15, true));
		if (dropGear) {
			addSequential(new SolenoidReverse(gearHandler));
		}
		addSequential(new RunProfileTwoSides(drive, leftBackupProfile, rightBackupProfile, 10));
		addSequential(new RunProfile(drive, forwardsProfile, 5));
	}
}
