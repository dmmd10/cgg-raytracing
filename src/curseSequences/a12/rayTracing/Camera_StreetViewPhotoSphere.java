package curseSequences.a12.rayTracing;

import static cgtools.Vec3.*;
import static cgtools.Mat4.*;

import cgtools.Mat4;
import cgtools.Vec3;

public class Camera_StreetViewPhotoSphere implements Camera {
	public final double angle;
	public final double cylRadius;
	public final double width;
	public final double height;
	public final double projectionHeight;
	
	public final Vec3 x0 = vec3(0);
	public final Mat4 transformMat;
	
	public Camera_StreetViewPhotoSphere(double angle, double cylRadius, double width, double height) {
		this(angle, cylRadius, width, height, translate(vec3(0)));
	}
	
	public Camera_StreetViewPhotoSphere(double angle, double cylRadius, double width, double height, Mat4 transformMat) {
		this.angle = angle;
		this.cylRadius = cylRadius;
		this.width = width;
		this.height = height;
		projectionHeight = Math.tan(angle/2) * 2 * cylRadius;
		this.transformMat = transformMat;
	}
	
	public Ray generateRay(double x, double y) {
		double alpha = x * 2 * Math.PI / width; 
		Vec3 x0$ = transformMat.transformPoint(x0);
		Vec3 d = normalize(vec3(cylRadius * Math.sin(alpha), projectionHeight / 2 - y * projectionHeight / height, -cylRadius * Math.cos(alpha)));
		Vec3 d$ = transformMat.transformDirection(d);
		return new Ray(x0$, d$, 0.0);
	}

	
}
