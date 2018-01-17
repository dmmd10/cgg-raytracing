package curseSequences.a03;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Sphere {

	protected final Vec3 center;
	protected final double radius;
	protected final double T_MIN;
	protected final double T_MAX;

	public Sphere(Vec3 center, double radius, double T_MIN, double T_MAX) {
		this.center = center;
		this.radius = radius;
		this.T_MIN = T_MIN;
		this.T_MAX = T_MAX;
	}

	public Hit intersect(Ray ray) {
		// r² = skalar
		Vec3 d = ray.getD();	// d = Richtungsvektor Ray
		Vec3 x0 = ray.getX0();	// x0 = Ursprungsvektor

		double a = Vec3.dotProduct(d, d);
		double b = Vec3.dotProduct(Vec3.multiply(2, x0), d) 
				   - Vec3.dotProduct(Vec3.multiply(2, d), center);
		double c = Vec3.dotProduct(Vec3.multiply(-2, x0), center) 
				   + Vec3.dotProduct(x0, x0)
				   + Vec3.dotProduct(center, center) 
				   - radius * radius;

		double discriminante = b * b - 4 * a * c;
		double t = -1; 
		
		Vec3 hitPoint = null;
		Vec3 normal = null;
		if (discriminante < 0) {
			return null;
		}
		if (discriminante == 0) {
			t = (-b + Math.sqrt(discriminante)) / 2*a;
			if (T_MIN > t || t > T_MAX) {
				return null;
			}
			hitPoint = Vec3.add(x0, Vec3.multiply(t, d));
			normal = Vec3.normalize(Vec3.subtract(hitPoint, center));
			return new Hit(t, hitPoint, normal);
		} else {
			double t1 = (-b + Math.sqrt(discriminante)) / 2*a;
			double t2 = (-b - Math.sqrt(discriminante)) / 2*a;
			
			if (T_MIN <= t1 && t1 <= T_MAX && (T_MIN > t2 || t2 > T_MAX)) {
				t = t1;
			}
			if (T_MIN <= t2 && t2 <= T_MAX && (T_MIN > t1 || t1 > T_MAX)) {
				t = t2;
			}
			if (T_MIN <= t1 && t1 <= T_MAX && (T_MIN <= t2 || t2 <= T_MAX)) {
				t = Math.min(t1, t2);
			} else {
				return null;
			}
			
			hitPoint = Vec3.add(x0, Vec3.multiply(t, d));
			normal = Vec3.normalize(Vec3.subtract(hitPoint, center));
			return new Hit(t, hitPoint, normal);
		}
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

}
