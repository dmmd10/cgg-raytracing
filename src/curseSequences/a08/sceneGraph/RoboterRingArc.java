package curseSequences.a08.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a08.rayTracing.Transformation;

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
		
		Mat4 translation = translate(vec3(0, 0, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), 180);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);
		Mat4 transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new RoboterQuarterArc(new Transformation(transformation)));
		
//		translation = translate(vec3(0, 0, 0));
//		rotationY = rotate(vec3(0, 1, 0), 0);
//		rotationZ = rotate(vec3(0, 0, 1), 180);
//		transformation = translation.multiply(rotationZ.multiply(rotationY));
//		geoObjectList.add(new QuarterSphereArc(new Transformation(transformation)));
//		
//		translation = translate(vec3(0, 0, 0));
//		rotationY = rotate(vec3(0, 1, 0), 180);
//		rotationZ = rotate(vec3(0, 0, 1), 180);
//		transformation = translation.multiply(rotationZ.multiply(rotationY));
//		geoObjectList.add(new QuarterSphereArc(new Transformation(transformation)));
	}
		
}
