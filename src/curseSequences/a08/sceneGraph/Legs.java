package curseSequences.a08.sceneGraph;

import static cgtools.Vec3.*;

import curseSequences.a08.materials.PerfectDiffuseMaterial;
import curseSequences.a08.materials.RoughMetalMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cone;
import curseSequences.a08.sceneObjects.Cylinder;

public class Legs extends Group {

	public Legs(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
		
		double legDistance = 1.2;

		geoObjectList.add(new Cone(vec3(0, 1, legDistance), 1, new PerfectDiffuseMaterial(lightYellow)));
		geoObjectList.add(new Cone(vec3(0, 1, -legDistance), 1, new PerfectDiffuseMaterial(lightYellow)));
		
		geoObjectList.add(new Cylinder(vec3(0, 0.5, legDistance), 0.5, 3.1, new RoughMetalMaterial(orange, 0.1)));
		geoObjectList.add(new Cylinder(vec3(0, 0.5, -legDistance), 0.5, 3.1, new RoughMetalMaterial(orange, 0.1)));
	}

	
	
}
