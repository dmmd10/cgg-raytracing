package curseSequences.a04;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Camera {
	protected double angle;
	protected double wide;
	protected double height;
	protected Vec3 x0 = vec3(0);
	
	public Camera(double angle, double wide, double height) {
		this.angle = angle;
		this.wide = wide;
		this.height = height;
	}
	
	public Ray generateRay(double x, double y) {
		Vec3 d = vec3(x - wide/2, height/2 - y, (-wide/2) / Math.tan(angle/2));
		return new Ray(x0, normalize(d));
	}

	@Override
	public String toString() {
		return "Camera [angle=" + angle + ", wide=" + wide + ", height=" + height + "]";
	}
}
