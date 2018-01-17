package curseSequences.a09.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import curseSequences.a09.materials.MetalMaterial;
import curseSequences.a09.rayTracing.Transformation;
import curseSequences.a09.sceneObjects.Cube;

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
				
		geoObjectList.add(new Cube(vec3(0, 0, 0), vec3(20, 20, 20), new MetalMaterial(light_blue)));
	}

}
