package curseSequences.a10.sceneGraph;

import static cgtools.Vec3.*;

import cgtools.Mat4;

import static cgtools.Mat4.*;

import curseSequences.a10.materials.ConstantTexture;
import curseSequences.a10.materials.RoughMetalMaterial;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Cylinder;


public class Roboter extends Group {

	public Roboter(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		double roboterWidth = 6.0;
		double roboterDepth = 3.4;
		Mat4 transformation = translate(vec3(-roboterDepth/2, -0.05, -roboterWidth/2));
		intersectTestShape = new RoboterTestBox(transformation);
		
		geoObjectList.add(new Legs(translate(vec3(0, 0, 0))));
		geoObjectList.add(new Arms(translate(vec3(0, 4.7, 0))));
		geoObjectList.add(new Head(translate(vec3(0, 9.7, 0))));
		
		// Bauch
		geoObjectList.add(new Cylinder(vec3(0, 3.7, 0), 1.7, 6.0, new RoughMetalMaterial(new ConstantTexture(light_blue), 0.1)));
	}

	
}
