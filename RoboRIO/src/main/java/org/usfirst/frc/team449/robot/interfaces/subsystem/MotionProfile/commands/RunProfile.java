package org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.jetbrains.annotations.NotNull;
import org.usfirst.frc.team449.robot.interfaces.subsystem.MotionProfile.TwoSideMPSubsystem.TwoSideMPSubsystem;
import org.usfirst.frc.team449.robot.util.MotionProfileData;
import org.usfirst.frc.team449.robot.util.YamlCommandGroupWrapper;
import org.usfirst.frc.team449.robot.util.YamlSubsystem;

/**
 * Loads and runs the given profile into the given subsystem.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class RunProfile <T extends YamlSubsystem & TwoSideMPSubsystem> extends YamlCommandGroupWrapper {

	/**
	 * Default constructor.
	 *
	 * @param subsystem The subsystem to execute this command on.
	 * @param profile   The motion profile to load and execute.
	 * @param timeout   The maximum amount of time this command is allowed to take, in seconds.
	 */
	@JsonCreator
	public RunProfile(@NotNull @JsonProperty(required = true) T subsystem,
	                  @NotNull @JsonProperty(required = true) MotionProfileData profile,
	                  @JsonProperty(required = true) double timeout) {
		addSequential(new LoadProfile(subsystem, profile));
		addSequential(new RunLoadedProfile<>(subsystem, timeout, true));
	}
}
