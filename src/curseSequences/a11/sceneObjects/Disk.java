package curseSequences.a11.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a11.materials.Material;
import curseSequences.a11.rayTracing.BoundingBox;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;

public class Disk implements Shape {

	public final Material material;
	public final Vec3 origin;
	public Vec3 normal;
	public final double radius;
	
	public Disk(Vec3 origin, double radius, Material material) {
		this.origin = origin;
		this.normal = vec3(0, 1, 0);
		this.radius = radius;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		
		double criteria = dotProduct(ray.d, normal);
		if (criteria == 0) {
			return null;
		} 
		double t = dotProduct(subtract(origin, ray.x0), normal) / criteria;
		if (ray.contains(t)) {
			Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
			Vec3 v = subtract(hitPoint, origin); 
	        double d2 = dotProduct(v, v);
			if (Math.sqrt(d2) <= radius) {
				if (dotProduct(normal, ray.d) > 0){
					normal = multiply(-1, normal);
				}
				double xPos2U = (hitPoint.x - origin.x) / radius + 0.5;
				double zPos2V = (hitPoint.z - origin.z) / radius + 0.5;
				return new Hit(t, hitPoint, normal,  new Vec3(xPos2U, zPos2V, 0), material, toString());
			}
		} 
		return null;
	}
	
	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(origin.x - radius, origin.y - 0.01, origin.z - radius);
		Vec3 max = vec3(origin.x + radius, origin.y + 0.01, origin.z + radius);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Disk [origin=" + origin + ", radius=" + radius + "]";
	}
}
