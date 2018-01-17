package curseSequences.a08.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;


import cgtools.Mat4;
import curseSequences.a08.materials.MetalMaterial;
import curseSequences.a08.materials.PerfectDiffuseMaterial;
import curseSequences.a08.materials.RoughMetalMaterial;
import curseSequences.a08.rayTracing.Transformation;
import curseSequences.a08.sceneObjects.Cube;

public class CubeRotated extends Group {

	public CubeRotated(Transformation transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
				
		Mat4 rotationX = rotate(vec3(0, 1, 0), -45);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 45);
		Mat4 transformation = rotationX.multiply(rotationZ);
		geoObjectList.add(new Cube(vec3(0, 0, 0), vec3(20, 20, 20), new MetalMaterial(light_blue)));
		
	}
}
