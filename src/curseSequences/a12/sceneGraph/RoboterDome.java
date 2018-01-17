package curseSequences.a12.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;

public class RoboterDome extends Group {
	
	public final int yOffset = -20;
	
	public RoboterDome(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		geoObjectList.add(new RoboterHalfRing(translate(vec3(0, yOffset, 0)))); // 45 90 135
		geoObjectList.add(new RoboterHalfRing(halfRingTrans(45)));
		geoObjectList.add(new RoboterHalfRing(halfRingTrans(90)));
		geoObjectList.add(new RoboterHalfRing(halfRingTrans(135)));
	}
	
	private Mat4 halfRingTrans(double angleY) {
		Mat4 translation = translate(vec3(0, yOffset, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);
		return translation.multiply(rotationZ.multiply(rotationY));
	} 
}
