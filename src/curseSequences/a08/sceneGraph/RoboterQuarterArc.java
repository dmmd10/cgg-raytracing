package curseSequences.a08.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a08.materials.MetalMaterial;
import curseSequences.a08.materials.PerfectDiffuseMaterial;
import curseSequences.a08.materials.RoughMetalMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cylinder;
import curseSequences.a08.sceneObjects.Sphere;

public class RoboterQuarterArc extends Group {

	public RoboterQuarterArc(Transformation transformation) {
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
		double winkel1 = 0;
		double winkel2 = (Math.PI/2) * 0.365;
		double winkel3 = (Math.PI/2) * 0.73;
		double height2 = Math.sin(winkel2)*radius;
		double x2 = Math.sqrt(radius * radius - height2 * height2);
		double height3 = Math.sin(winkel3)*radius;
		double x3 = Math.sqrt(radius * radius - height3 * height3);
		
		Mat4 transformation = translate(vec3(-radius, 0, 0));
		geoObjectList.add(new Roboter(new Transformation(transformation)));
		
		Mat4 translation = translate(vec3(-x2, height2, 0));
		Mat4 rotationY = rotate(vec3(0, 1, 0), 0);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), Math.toDegrees(-winkel2));
		transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new Roboter(new Transformation(transformation)));
		
		translation = translate(vec3(-x3, height3, 0));
		rotationY = rotate(vec3(0, 1, 0), 0);
		rotationZ = rotate(vec3(0, 0, 1), Math.toDegrees(-winkel3));
		transformation = translation.multiply(rotationZ.multiply(rotationY));
		geoObjectList.add(new Roboter(new Transformation(transformation)));
	}
}
