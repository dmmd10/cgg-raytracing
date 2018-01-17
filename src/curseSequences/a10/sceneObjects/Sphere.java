package curseSequences.a10.sceneObjects;

import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a10.materials.Material;
import curseSequences.a10.rayTracing.BoundingBox;
import curseSequences.a10.rayTracing.Hit;
import curseSequences.a10.rayTracing.Ray;

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
				
		Vec3 minus = subtract(ray.x0, center);
		double a = dotProduct(ray.d, ray.d);
		double b = 2 * dotProduct(ray.d, minus);
		double c = dotProduct(minus, minus) - radius * radius;
		
		double discriminante = b * b - 4 * a * c;
		double t = -1; 
		
		Vec3 hitPoint = null;
		Vec3 normal = null;
		double inclination = 0;
	    double azimuth = 0;
	    double u = 0;
	    double v = 0;
	    
		if (discriminante < 0) {
			return null;
		} else {
			double t1 = (-b - Math.signum(b) * Math.sqrt(discriminante)) / (2 * a);
		    double t2 = c / (a * t1);
		    if (t1 > t2) {
		        double tmp = t1;
		        t1 = t2;
		        t2 = tmp;
		    }
			
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
			inclination = Math.acos(normal.y);
		    azimuth = Math.PI + Math.atan2(normal.x, normal.z);
		    u = azimuth / (2 * Math.PI);
		    v = inclination / Math.PI;
			return new Hit(t, hitPoint, normal, vec3(u, v, 0), material);
		}
	}
	
	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(center.x - radius, center.y - radius, center.z - radius);
		Vec3 max = vec3(center.x + radius, center.y + radius, center.z + radius);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}
}