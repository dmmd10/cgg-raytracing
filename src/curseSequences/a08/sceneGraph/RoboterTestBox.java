package curseSequences.a08.sceneGraph;

import static cgtools.Vec3.*;

import curseSequences.a08.materials.EmittingMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cube;

public class RoboterTestBox extends Group {
	public RoboterTestBox(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		double roboterHeight = 13.15;
		double roboterWidth = 6.0;
		double roboterDepth = 3.4;

		geoObjectList.add(new Cube(vec3(0, 0, 0), vec3(roboterDepth, roboterHeight, roboterWidth), new EmittingMaterial(white)));
	}
}
