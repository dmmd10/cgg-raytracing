package curseSequences.a08.sceneGraph;


import static cgtools.Vec3.*;

import curseSequences.a08.materials.EmittingMaterial;
import curseSequences.a08.materials.GlassMaterial;
import curseSequences.a08.materials.PerfectDiffuseMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cone;
import curseSequences.a08.sceneObjects.Sphere;


public class Head extends Group {

	public Head(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		// Hals
		geoObjectList.add(new Cone(vec3(0, 1, 0), 1, new PerfectDiffuseMaterial(black)));

		// Kopf
		geoObjectList.add(new Sphere(vec3(0, 1.8, 0), 1.6, new GlassMaterial(aqua, 1.5)));

		// Augen
		double eyeDistance = 0.7;
		geoObjectList.add(new Sphere(vec3(1.3, 1.7, eyeDistance), 0.3, new EmittingMaterial(shiny_red)));
		geoObjectList.add(new Sphere(vec3(1.3, 1.7, -eyeDistance), 0.3, new EmittingMaterial(shiny_red)));
	}

}
