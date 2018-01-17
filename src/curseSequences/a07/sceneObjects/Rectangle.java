package curseSequences.a07.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a07.materials.Material;
import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.Ray;

public class Rectangle implements Shape {

	public final Material material;
	public final Vec3 origin;
	public Vec3 normal;
	public final Vec3 diaCorner;
	
	public Rectangle(Vec3 origin, Vec3 normal, Vec3 diaCorner, Material material) {
		this.origin = origin;
		this.normal = normal;
		this.diaCorner = diaCorner;
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
			//hier weiter
	        double d2 = dotProduct(v, v);
			if (Math.sqrt(d2) <= 0) {
				if (dotProduct(normal, ray.d) > 0){
					normal = multiply(-1, normal);
				}
				return new Hit(t, hitPoint, normal, material);
			}
		} 
		return null;
	}
}
