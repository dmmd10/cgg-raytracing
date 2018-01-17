package curseSequences.a08.sceneGraph;

import static cgtools.Vec3.*;

import curseSequences.a08.materials.MetalMaterial;
import curseSequences.a08.materials.RoughMetalMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cylinder;
import curseSequences.a08.sceneObjects.Sphere;

public class Arms extends Group {

	public Arms(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		// Arme
		double armDistance = 2.5;
		geoObjectList.add(new Cylinder(vec3(0, 0, armDistance), 0.4, 4.0, new MetalMaterial(marine)));
		geoObjectList.add(new Cylinder(vec3(0, 0, -armDistance), 0.4, 4.0, new MetalMaterial(marine)));

		// Schulter
		double shoulderDistance = 2.3;
		geoObjectList.add(new Sphere(vec3(0, 3.8, shoulderDistance), 0.7, new RoughMetalMaterial(white, 0.1)));
		geoObjectList.add(new Sphere(vec3(0, 3.8, -shoulderDistance), 0.7, new RoughMetalMaterial(white, 0.1)));
	}
}
