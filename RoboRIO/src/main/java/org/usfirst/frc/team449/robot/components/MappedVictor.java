package org.usfirst.frc.team449.robot.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * A wrapper for a {@link VictorSP} allowing it to be easily constructed from a map object.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class)
public class MappedVictor extends VictorSP {

	/**
	 * Json constructor using a port and inversion.
	 *
	 * @param port     The port number of the Victor.
	 * @param inverted Whether the motor is inverted. Defaults to false.
	 */
	@JsonCreator
	public MappedVictor(@JsonProperty(required = true) int port,
	                    boolean inverted) {
		super(port);
		this.setInverted(inverted);
	}
}
