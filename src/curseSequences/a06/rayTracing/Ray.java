package curseSequences.a06.rayTracing;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Ray {
	public final Vec3 x0;
	public final Vec3 d;
	public final double T_MIN;
	public final double T_MAX;
	
	public Ray(Vec3 x0, Vec3 d) {
		this(x0, d, 0.0001);
	}
	
	public Ray(Vec3 x0, Vec3 d, double T_MIN) {
		this(x0, d, T_MIN, Double.POSITIVE_INFINITY);
	}
	
	public Ray(Vec3 x0, Vec3 d, double T_MIN, double T_MAX) {
		this.x0 = x0;
		this.d = d;
		this.T_MIN = T_MIN;
		this.T_MAX = T_MAX;
	}
	
	public Vec3 pointAt(double t) {
		return add(x0, multiply(t, d));
	}

	@Override
	public String toString() {
		return "Ray [x0=" + x0 + ", d=" + d + "]";
	}	
}
