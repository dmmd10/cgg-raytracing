package curseSequences.a09.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a09.materials.Material;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public class Disk implements Shape {

	public final Material material;
	public final Vec3 origin;
	public Vec3 normal;
	public final double radius;
	
	public Disk(Vec3 origin, Vec3 normal, double radius, Material material) {
		this.origin = origin;
		this.normal = normal;
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
		if (ray.T_MIN <= t && t <= ray.T_MAX) {
			Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
			Vec3 v = subtract(hitPoint, origin); 
	        double d2 = dotProduct(v, v);
			if (Math.sqrt(d2) <= radius) {
				if (dotProduct(normal, ray.d) > 0){
					normal = multiply(-1, normal);
				}
				return new Hit(t, hitPoint, normal, material);
			}
		} 
		return null;
	}
}
