package curseSequences.a10.rayTracing;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import cgtools.Mat4;
import cgtools.Vec3;

public class Camera_Standard implements Camera {
	public final double angle;
	public final double wide;
	public final double height;
	public final Vec3 x0 = vec3(0);
	public final Mat4 transformMat;
	
	public Camera_Standard(double angle, double wide, double height) {
		this(angle, wide, height, translate(vec3(0)));
	}
	
	public Camera_Standard(double angle, double wide, double height, Mat4 transformMat) {
		this.angle = angle;
		this.wide = wide;
		this.height = height;
		this.transformMat = transformMat;
	}
	
	public Ray generateRay(double x, double y) {
		Vec3 x0$ = transformMat.transformPoint(x0);
		Vec3 d = normalize(vec3(x - wide/2, height/2 - y, (-wide/2) / Math.tan(angle/2)));
		Vec3 d$ = transformMat.transformDirection(d);
		return new Ray(x0$, d$, 0.0);
	}

	@Override
	public String toString() {
		return "Camera_Standard [angle=" + angle + ", wide=" + wide + ", height=" + height + "]";
	}
}
