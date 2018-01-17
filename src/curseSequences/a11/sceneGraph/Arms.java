package curseSequences.a11.sceneGraph;

import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a11.materials.ConstantTexture;
import curseSequences.a11.materials.MetalMaterial;
import curseSequences.a11.materials.RoughMetalMaterial;
import curseSequences.a11.rayTracing.Transformation;
import curseSequences.a11.sceneObjects.Cylinder;
import curseSequences.a11.sceneObjects.Sphere;

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
