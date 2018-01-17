package curseSequences.a04;

import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Sphere implements Shape {

	protected final Vec3 center;
	protected final double radius;
	protected final Vec3 color;

	public Sphere(Vec3 center, double radius, Vec3 color) {
		this.center = center;
		this.radius = radius;
		this.color = color;
	}

	@Override
	public Hit intersect(Ray ray) {
				
		double a = dotProduct(ray.d, ray.d);
		double b = dotProduct(multiply(2, ray.x0), ray.d) 
				   - dotProduct(multiply(2, ray.d), center);
		double c = dotProduct(multiply(-2, ray.x0), center) 
				   + dotProduct(ray.x0, ray.x0)
				   + dotProduct(center, center) 
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
			hitPoint = add(ray.x0, multiply(t, ray.d));
			normal = normalize(subtract(hitPoint, center));
			return new Hit(t, hitPoint, normal, normal);
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
			hitPoint = add(ray.x0, multiply(t, ray.d));
			normal = normalize(subtract(hitPoint, center));
			Vec3 calcColor = multiply(0.15 + normal.z * 0.85, color);
			return new Hit(t, hitPoint, normal, calcColor);
		}
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

}
