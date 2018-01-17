package curseSequences.a10.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a10.rayTracing.Transformation;

public class RoboterSphere extends Group {
	
	
	public RoboterSphere(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		geoObjectList.add(new RoboterRingArc(translate(vec3(0, 0, 0)))); // 45 90 135
//		geoObjectList.add(new RoboterRingArc(ringArcTrans(45)));
		geoObjectList.add(new RoboterRingArc(ringArcTrans(90)));
//		geoObjectList.add(new RoboterRingArc(ringArcTrans(135)));
	}
	
	private Mat4 ringArcTrans(double angleY) {
		Mat4 translation = translate(vec3(0, 0, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);
		return translation.multiply(rotationZ.multiply(rotationY));
	} 
}
