package curseSequences.a07.rayTracing;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import cgtools.Mat4;
import cgtools.Vec3;

//		|+Y
//		|____+X
//	   /
//    /+z

public class CameraViews {

	public static Mat4 viewDownwardCenter() {
		Mat4 translation = translate(vec3(0, 22, 0)); //von oben
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 90);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 viewRightsideDy0() {
		Mat4 translation = translate(vec3(4, 4.5, 0)); //von vorn
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 0);		
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 viewRightsideSkewed45() {
		Mat4 translation = translate(vec3(40, 40, 0)); //von vorn schräg nach unten
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 45);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
}
