package curseSequences.a10.sceneObjects;

import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a10.materials.Material;
import curseSequences.a10.rayTracing.BoundingBox;
import curseSequences.a10.rayTracing.Hit;
import curseSequences.a10.rayTracing.Ray;

public class Rectangle implements Shape {

	public final Material material;
	public final Vec3 origin;
	public final double width;
	public final double depth;
	public Vec3 normal;

	public Rectangle(Vec3 origin, double width, double depth, Material material) {
		this.origin = origin;
		this.normal = new Vec3(0, 1, 0);
		this.width = width;
		this.depth = depth;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {

		double criteria = dotProduct(ray.d, normal);
		if (criteria == 0) {
			return null;
		}
		double t = (origin.y - ray.x0.y) / ray.d.y;
		if (t < ray.T_MIN) {
			return null;
		}
		Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
		boolean withinXRange = (origin.x <= hitPoint.x) && (hitPoint.x <= (origin.x + width));
		boolean withinZRange = (origin.z <= hitPoint.z) && (hitPoint.z <= (origin.z + depth));
		if (withinXRange && withinZRange) {
			if (dotProduct(normal, ray.d) > 0) {
				normal = vec3(0, -1, 0);
			}
			double xPos2U = (hitPoint.x - origin.x) / width;
			double zPos2V = (hitPoint.z - origin.z) / depth;
			return new Hit(t, hitPoint, normal, new Vec3(xPos2U, zPos2V, 0), material);
		}
		return null;
	}
	
	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(origin.x, origin.y - 0.01, origin.z);
		Vec3 max = vec3(origin.x + width, origin.y + 0.01, origin.z + depth);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Rectangle [origin(MinPoint)=" + origin + ", width=" + width + ", depth=" + depth + "]";
	}
}
