package curseSequences.a09.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a09.rayTracing.Transformation;

public class RoboterSphere extends Group {
	
	
	public RoboterSphere(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		geoObjectList.add(new RoboterRingArc(new Transformation(translate(vec3(0, 0, 0)))));
//		geoObjectList.add(new RoboterRingArc(ringArcTrans(90)));
//		geoObjectList.add(new RoboterRingArc(ringArcTrans(45)));
//		geoObjectList.add(new RoboterRingArc(ringArcTrans(135)));
	}
	
	private Transformation ringArcTrans(double angleY) {
		Mat4 translation = translate(vec3(0, 0, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);
		Mat4 transformation = translation.multiply(rotationZ.multiply(rotationY));
		return new Transformation(transformation);
	} 
}
