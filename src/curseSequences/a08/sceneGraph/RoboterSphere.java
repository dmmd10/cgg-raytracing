package curseSequences.a08.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a08.rayTracing.Transformation;

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
		
		Mat4 translation = translate(vec3(0, 0, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);
		Mat4 transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new RoboterRingArc(new Transformation(transformation)));

		rotationY = rotate(vec3(0, 1, 0), 45);
		transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new RoboterRingArc(new Transformation(transformation)));
		rotationY = rotate(vec3(0, 1, 0), 135);
		transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new RoboterRingArc(new Transformation(transformation)));
	}
}
