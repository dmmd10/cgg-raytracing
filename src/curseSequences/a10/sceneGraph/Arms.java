package curseSequences.a10.sceneGraph;

import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a10.materials.ConstantTexture;
import curseSequences.a10.materials.MetalMaterial;
import curseSequences.a10.materials.RoughMetalMaterial;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Cylinder;
import curseSequences.a10.sceneObjects.Sphere;

public class Arms extends Group {

	public Arms(Mat4 transformation) {
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
		geoObjectList.add(new Cylinder(vec3(0, 0, armDistance), 0.4, 4.0, new MetalMaterial(new ConstantTexture(marine))));
		geoObjectList.add(new Cylinder(vec3(0, 0, -armDistance), 0.4, 4.0, new MetalMaterial(new ConstantTexture(marine))));

		// Schulter
		double shoulderDistance = 2.3;
		geoObjectList.add(new Sphere(vec3(0, 3.8, shoulderDistance), 0.7, new RoughMetalMaterial(new ConstantTexture(white), 0.1)));
		geoObjectList.add(new Sphere(vec3(0, 3.8, -shoulderDistance), 0.7, new RoughMetalMaterial(new ConstantTexture(white), 0.1)));
	}
}
