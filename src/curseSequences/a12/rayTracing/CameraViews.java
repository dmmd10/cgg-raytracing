package curseSequences.a12.rayTracing;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import cgtools.Mat4;
import cgtools.Vec3;

//		|+Y
//		|____+X
//	   /
//    /+z

public class CameraViews {

	public static Mat4 lookDownCenterPerspective() {
		Mat4 translation = translate(vec3(0, 80, 0)); //80
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 90);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 lookUpCenterPerspective() {
		Mat4 translation = translate(vec3(0, -80, 0)); //80
		Mat4 rotationY = rotate(vec3(0, 1, 0), 90);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), -90);
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 sidePerspective1() {
		Mat4 translation = translate(vec3(60, 1, 10)); //vec3(60, 1, 10)
		Mat4 rotationY = rotate(vec3(0, 1, 0), 80);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), -20); //vec3(0, 0, 1), -10)
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 sidePerspective2() {
		Mat4 translation = translate(vec3(0, 10, 10)); //vec3(60, 1, 10)
		Mat4 rotationY = rotate(vec3(0, 1, 0), -65);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), -2); //vec3(0, 0, 1), -10)
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 sidePerspective3() {
		Mat4 translation = translate(vec3(150, 60, 10)); //vec3(60, 1, 10)
		Mat4 rotationY = rotate(vec3(0, 1, 0), 80);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 10); //vec3(0, 0, 1), -10)
		return translation.multiply(rotationZ.multiply(rotationY));
	}
	
	public static Mat4 sidePerspective4() {
		Mat4 translation = translate(vec3(25, 10, 10)); //vec3(60, 1, 10)
		Mat4 rotationY = rotate(vec3(0, 1, 0), 80);
		Mat4 rotationZ = rotate(vec3(0, 0, 1), 10); //vec3(0, 0, 1), -10)
		return translation.multiply(rotationZ.multiply(rotationY));
	}
}
