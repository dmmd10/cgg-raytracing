package curseSequences.a04;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Ray {
	public final Vec3 x0;
	public final Vec3 d;
	
	public Ray(Vec3 x0, Vec3 d) {
		this.x0 = x0;
		this.d = d;
	}
	
	public Vec3 pointAt(double t) {
		return add(x0, multiply(t, d));
	}

	@Override
	public String toString() {
		return "Ray [x0=" + x0 + ", d=" + d + "]";
	}	
}
