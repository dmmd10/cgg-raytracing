package curseSequences.a10.sceneGraph;

import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a10.materials.ConstantTexture;
import curseSequences.a10.materials.PerfectDiffuseMaterial;
import curseSequences.a10.materials.RoughMetalMaterial;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Cone;
import curseSequences.a10.sceneObjects.Cylinder;

public class Legs extends Group {

	public Legs(Mat4 transformation) {
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

		geoObjectList.add(new Cone(vec3(0, 1, legDistance), 1, new PerfectDiffuseMaterial(new ConstantTexture(lightYellow))));
		geoObjectList.add(new Cone(vec3(0, 1, -legDistance), 1, new PerfectDiffuseMaterial(new ConstantTexture(lightYellow))));
		
		geoObjectList.add(new Cylinder(vec3(0, 0.5, legDistance), 0.5, 3.1, new RoughMetalMaterial(new ConstantTexture(orange), 0.1)));
		geoObjectList.add(new Cylinder(vec3(0, 0.5, -legDistance), 0.5, 3.1, new RoughMetalMaterial(new ConstantTexture(orange), 0.1)));
	}

	
	
}
