package curseSequences.a10.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a10.materials.MetalMaterial;
import curseSequences.a10.materials.PerfectDiffuseMaterial;
import curseSequences.a10.materials.RoughMetalMaterial;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Cylinder;
import curseSequences.a10.sceneObjects.Sphere;

public class RoboterQuarterArc extends Group {

	public RoboterQuarterArc(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		double radius = 30;
		double winkel2 = (Math.PI/2) * 0.365;
		double winkel3 = (Math.PI/2) * 0.73;
		double height2 = Math.sin(winkel2)*radius;
		double x2 = Math.sqrt(radius * radius - height2 * height2);
		double height3 = Math.sin(winkel3)*radius;
		double x3 = Math.sqrt(radius * radius - height3 * height3);
		
		Mat4 transformation = translate(vec3(-radius, 0, 0));
		geoObjectList.add(new Roboter(transformation));
		geoObjectList.add(new Roboter(arcTrans(x2, height2, winkel2)));
		geoObjectList.add(new Roboter(arcTrans(x3, height3, winkel3)));
	}
	
	private Mat4 arcTrans(double x, double height, double angleZ) {
		Mat4 translation = translate(vec3(-x, height, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), 0);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), Math.toDegrees(-angleZ));
		return translation.multiply(rotationZ.multiply(rotationY));
	}
}
