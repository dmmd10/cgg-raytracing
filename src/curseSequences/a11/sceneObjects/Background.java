package curseSequences.a11.sceneObjects;

import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a11.materials.Material;
import curseSequences.a11.rayTracing.BoundingBox;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;

public class Background implements Shape {

	public final Material material;
	
	public Background(Material material) {
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		double t = Double.POSITIVE_INFINITY;
		if(!ray.contains(t)) {
			return null;
		}
		Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
		Vec3 normal = normalize(ray.d);
		double inclination = Math.acos(normal.y);
	    double azimuth = Math.PI + Math.atan2(normal.x, normal.z);
	    double u = azimuth / (2 * Math.PI);
	    double v = inclination / Math.PI;
		return new Hit(t, hitPoint, normal, new Vec3(u, v, 0), material, toString());
	}
	
	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(Double.NEGATIVE_INFINITY);
		Vec3 max = vec3(Double.POSITIVE_INFINITY);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Background [material=" + material + "]";
	}
}
