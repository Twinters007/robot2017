package org.usfirst.frc.team449.robot.autonomous;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.jetbrains.annotations.NotNull;
import org.usfirst.frc.team449.robot.components.MappedDigitalInput;
import org.usfirst.frc.team449.robot.util.YamlCommand;
import org.usfirst.frc.team449.robot.util.YamlCommandGroupWrapper;
import org.usfirst.frc.team449.robot.util.YamlSubsystem;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class FeederAuto2017 extends YamlCommandGroupWrapper {

	/**
	 * Default constructor.
	 *
	 * @param runWallToPegProfile The command for running the profile for going from the wall to the peg, which has already been loaded.
	 * @param dropGear The command for dropping the held gear.
	 * @param dropGearSwitch      The switch deciding whether or not to drop the gear.
	 * @param allianceSwitch The switch indicating which alliance we're on.
	 * @param runRedBackupProfile The command for away from the peg, on the red side of the field.
	 * @param runBlueBackupProfile The command for moving away from the peg, on the blue side of the field.
	 * @param driveForwards    The command for moving forwards towards the feeder station..
	 */
	@JsonCreator
	public FeederAuto2017(@JsonProperty(required = true) @NotNull RunLoadedProfile runWallToPegProfile,
	                      @JsonProperty(required = true) @NotNull YamlCommand dropGear,
	                      @JsonProperty(required = true) @NotNull MappedDigitalInput dropGearSwitch,
	                      @JsonProperty(required = true) @NotNull MappedDigitalInput allianceSwitch,
	                      @JsonProperty(required = true) @NotNull RunProfileTwoSides runRedBackupProfile,
	                      @JsonProperty(required = true) @NotNull RunProfileTwoSides runBlueBackupProfile,
	                      @JsonProperty(required = true) @NotNull YamlCommand driveForwards) {
		addSequential(runWallToPegProfile);
		if (dropGearSwitch.getStatus().get(0)) {
			addSequential(dropGear.getCommand());
		}
		if (allianceSwitch.getStatus().get(0)) {
			addSequential(runRedBackupProfile);
		} else {
			addSequential(runBlueBackupProfile);
		}
		addSequential(driveForwards.getCommand());
	}
}
