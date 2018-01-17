package curseSequences.a11.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a11.rayTracing.Transformation;

public class RoboterHalfRing extends Group {
	
	public RoboterHalfRing(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		geoObjectList.add(new RoboterQuarterArc(translate(vec3(0, 20, 0))));
		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(180, 0)));
	}
	
	private Mat4 quarterArcTrans(double angleY,double angleZ) {
		Mat4 translation = translate(vec3(0, 20, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), angleZ);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
		
}
