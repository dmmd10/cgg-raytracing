package curseSequences.a10.sceneGraph;

import static cgtools.Mat4.*;
import static cgtools.Vec3.*;

import cgtools.Mat4;
import curseSequences.a10.materials.ColoredTexture;
import curseSequences.a10.materials.ConstantTexture;
import curseSequences.a10.materials.EmittingMaterial;
import curseSequences.a10.materials.MetalMaterial;
import curseSequences.a10.materials.PicTexture;
import curseSequences.a10.rayTracing.Transformation;
import curseSequences.a10.sceneObjects.Cube;

public class CubeRotated extends Group {
		
	public CubeRotated(Mat4 transformation) {
		super(transformation);
	}

	@Override
	public void generateShapesList() {
		
//		// Testkonfiguration
////	|+Y
////	|____+X
////   /
////  /+z
				
		geoObjectList.add(new Cube(vec3(0, 0, 0), vec3(20, 20, 20), new EmittingMaterial(new PicTexture("doc/cube_textur_linear.jpg"))));
	}

}
