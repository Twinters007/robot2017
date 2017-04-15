package org.usfirst.frc.team449.robot.vision.auto;

/**
 * Created by ryant on 2017-04-15.
 */
public class VPoint implements Comparable<VPoint> {

	public double x, y;

	public VPoint(double _x, double _y) {
		x = _x;
		y = _y;
	}

	public double distToOrigin() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public int compareTo(VPoint p) {
		return (int) (x - p.x);
	}
}
