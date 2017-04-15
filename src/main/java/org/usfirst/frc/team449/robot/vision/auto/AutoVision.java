package org.usfirst.frc.team449.robot.vision.auto;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.List;

import static org.usfirst.frc.team449.robot.vision.auto.GripPipeline.Line;

/**
 * Created by ryant on 2017-04-15.
 */
public class AutoVision {

	private static final int CAM_PIX_W = 0;
	private static final int CAM_PIX_H = 0;
	private static final double STRIP_H = 0;
	private static final double STRIP_W = 0;
	private static final double AR_H = 0;
	private static final double AR_W = 0;


	public static VPoint getOffsets() {
		VideoCapture vc = new VideoCapture();
		vc.open(0);

		Mat frame = new Mat();
		vc.retrieve(frame);

		GripPipeline gp = new GripPipeline();

		gp.process(frame);

		ArrayList<Line> lines = gp.findLinesOutput();

		List<VPoint> points = new ArrayList();
		for (int i = 0; i < lines.size(); i++) {
			points.add(new VPoint(lines.get(i).x1, lines.get(i).y1));
			points.add(new VPoint(lines.get(i).x2, lines.get(i).y2));
		}

		double min = Double.MAX_VALUE, max = 0, rep;

		int minI = 0, maxI = 0;

		for (int i = 0; i < points.size(); i++) {
			rep = points.get(i).distToOrigin();
			min = rep < min ? rep : min;
			minI = rep < min ? minI : i;
			max = rep > max ? rep : max;
			maxI = rep > max ? maxI : i;
		}

		double pixH = Math.abs(points.get(minI).y - points.get(maxI).y);
		double offW = CAM_PIX_W - (points.get(minI).x + points.get(maxI).x) / 2;

		double theta = pixH / 2 / CAM_PIX_H * AR_H;
		double phi = offW / CAM_PIX_W * AR_W;

		double d = pixH / 2 * Math.atan(theta);
		double offset = d * Math.tan(phi);

		System.out.println("D: " + d);
		System.out.println("Offset: " + offset);

		return new VPoint(d, offset);
	}
}
