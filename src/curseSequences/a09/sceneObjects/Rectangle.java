package curseSequences.a09.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a09.materials.Material;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public class Rectangle implements Shape {

	public final Material material;
	public final Vec3 origin;
	public final double width;
	public final double height;
	public Vec3 normal;
	
	public Rectangle(Vec3 origin, Vec3 normal, double width, double height, Material material) {
		this.origin = origin;
		this.normal = normal;
		this.width = width;
		this.height = height;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		if (normal.z == 1) {
			double criteria = dotProduct(ray.d, normal);
			if (criteria == 0) {
				return null;
			}
			double t = (origin.z - ray.x0.z) / ray.d.z;
			if (t < ray.T_MIN) {
				return null;
			}
			Vec3 hitPoint = add(ray.x0, multiply(t,ray.d));
			boolean withinXRange = (origin.x <= hitPoint.x) && (hitPoint.x <= (origin.x + width));
			boolean withinYRange = (origin.y <= hitPoint.y) && (hitPoint.y <= (origin.y + height));
			if (withinXRange && withinYRange) {
				if (dotProduct(normal, ray.d) > 0) {
					normal = vec3(0, 0, -1);
				}
				return new Hit(t, hitPoint, normal, material);
			}
		}
		
		if (normal.y == 1) {
			double criteria = dotProduct(ray.d, normal);
			if (criteria == 0) {
				return null;
			}
			double t = (origin.y - ray.x0.y) / ray.d.y;
			if (t < ray.T_MIN) {
				return null;
			}
			Vec3 hitPoint = add(ray.x0, multiply(t,ray.d));
			boolean withinXRange = (origin.x <= hitPoint.x) && (hitPoint.x <= (origin.x + width));
			boolean withinZRange = (origin.z <= hitPoint.z) && (hitPoint.z <= (origin.z + height));
			if (withinXRange && withinZRange) {
				if (dotProduct(normal, ray.d) > 0) {
					normal = vec3(0, -1, 0);
				}
				return new Hit(t, hitPoint, normal, material);
			}
		}
		
		if (normal.x == 1) {
			double criteria = dotProduct(ray.d, normal);
			if (criteria == 0) {
				return null;
			}
			double t = (origin.x - ray.x0.x) / ray.d.x;
			if (t < ray.T_MIN) {
				return null;
			}
			Vec3 hitPoint = add(ray.x0, multiply(t,ray.d));
			boolean withinYRange = (origin.y <= hitPoint.y) && (hitPoint.y <= (origin.y + width));
			boolean withinZRange = (origin.z <= hitPoint.z) && (hitPoint.z <= (origin.z + height));
			if (withinYRange && withinZRange) {
				if (dotProduct(normal, ray.d) > 0) {
					normal = vec3(-1, 0, 0);
				}
				return new Hit(t, hitPoint, normal, material);
			}
		}
		return null;
	}
	
	
}
