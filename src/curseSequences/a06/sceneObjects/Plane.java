package curseSequences.a06.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a06.materials.Material;
import curseSequences.a06.rayTracing.Hit;
import curseSequences.a06.rayTracing.Ray;

public class Plane implements Shape {

	public final Material material;
	public final Vec3 origin;
	public final Vec3 normal;
	
	public Plane(Vec3 origin, Vec3 normal, Material material) {
		this.origin = origin;
		this.normal = normal;
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
//			System.out.println("Ray auf Plane: hitPoint="+ hitPoint);
			return new Hit(t, hitPoint, normal, material);
		} 
		return null;
	}
}
