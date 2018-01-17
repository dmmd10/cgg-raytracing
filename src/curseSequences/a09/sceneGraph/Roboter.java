package curseSequences.a09.sceneGraph;

import static cgtools.Vec3.*;

import cgtools.Mat4;

import static cgtools.Mat4.*;

import curseSequences.a09.materials.RoughMetalMaterial;
import curseSequences.a09.rayTracing.Transformation;
import curseSequences.a09.sceneObjects.Cylinder;


public class Roboter extends Group {

	public Roboter(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
//		double roboterWidth = 6.0;
//		double roboterDepth = 3.4;
//		Mat4 transformation = translate(vec3(-roboterDepth/2, -0.05, -roboterWidth/2));
//		intersectTestShape = new RoboterTestBox(new Transformation(transformation));
		
		geoObjectList.add(new Legs(new Transformation(translate(vec3(0, 0, 0)))));
		geoObjectList.add(new Arms(new Transformation(translate(vec3(0, 4.7, 0)))));
		geoObjectList.add(new Head(new Transformation(translate(vec3(0, 9.7, 0)))));
		
		// Bauch
		geoObjectList.add(new Cylinder(vec3(0, 3.7, 0), 1.7, 6.0, new RoughMetalMaterial(light_blue, 0.1)));
	}

	
}
