package curseSequences.a05.sceneObjects;

import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a05.rayTracing.Hit;
import curseSequences.a05.rayTracing.Ray;
import curseSequences.a05.reflection.Material;

public class Sphere implements Shape {

	public final Vec3 center;
	public final double radius;
	public final Material material;

	public Sphere(Vec3 center, double radius, Material material) {
		this.center = center;
		this.radius = radius;
		this.material = material;
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
			return new Hit(t, hitPoint, normal, material);
		} else {
			double t1 = (-b + Math.sqrt(discriminante)) / 2*a;
			double t2 = (-b - Math.sqrt(discriminante)) / 2*a;
			
			boolean t1_Valid = ray.T_MIN <= t1 && t1 <= ray.T_MAX;
			boolean t2_Valid = ray.T_MIN <= t2 && t2 <= ray.T_MAX;
			
			
			if (t1_Valid && !t2_Valid) {
				t = t1;
			}
			if (t2_Valid && !t1_Valid) {
				t = t2;
			}
			if (t1_Valid && t2_Valid) {
				t = Math.min(t1, t2);
			} else {
				return null;
			}
			
			hitPoint = add(ray.x0, multiply(t, ray.d));
			normal = normalize(subtract(hitPoint, center));
			return new Hit(t, hitPoint, normal, material);
		}
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}

}
