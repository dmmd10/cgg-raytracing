package curseSequences.a03;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Ray {
	protected Vec3 x0;
	protected double t;
	protected Vec3 d;
	
	public Ray(Vec3 x0, Vec3 d) {
		this.x0 = x0;
		this.d = d;
	}
	
	public Vec3 pointAt(double t) {
		return Vec3.add(x0, Vec3.multiply(t, d));
	}

	public Vec3 getX0() {
		return x0;
	}

	public double getT() {
		return t;
	}

	public Vec3 getD() {
		return d;
	}

	@Override
	public String toString() {
		return "Ray [x0=" + x0 + ", t=" + t + ", d=" + d + "]";
	}	
}
