package curseSequences.a12.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;

public class RoboterRingArc extends Group {
	
	public RoboterRingArc(Mat4 transformation) {
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
		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(20, 180)));
		geoObjectList.add(new RoboterQuarterArc(quarterArcTrans(200, 180)));
	}
	
	private Mat4 quarterArcTrans(double angleY,double angleZ) {
		Mat4 translation = translate(vec3(0, 20, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), angleY);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), angleZ);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
		
}
