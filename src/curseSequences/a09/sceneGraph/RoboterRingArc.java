package curseSequences.a09.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a09.rayTracing.Transformation;

public class RoboterRingArc extends Group {
	
	public RoboterRingArc(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		geoObjectList.add(new RoboterQuarterArc(new Transformation(translate(vec3(0, 0, 0)))));
//		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(180, 0)));
//		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(0, 180)));
//		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(180, 180)));
	}
	
	private Transformation quarterArcTrans(double angleY,double angleZ) {
		Mat4 translation = translate(vec3(0, 0, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), angleZ);
		Mat4 transformation = translation.multiply(rotationZ.multiply(rotationY));
		return new Transformation(transformation);
	}
		
}
