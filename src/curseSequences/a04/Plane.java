package curseSequences.a04;
import static cgtools.Vec3.*;
import cgtools.Vec3;

public class Plane implements Shape {

	final Vec3 color;
	final Vec3 origin;
	final Vec3 normal;
	
	public Plane(Vec3 origin, Vec3 normal, Vec3 color) {
		this.origin = origin;
		this.normal = normal;
		this.color = color;
	}

	@Override
	public Hit intersect(Ray ray) {
		
		double criteria = dotProduct(ray.d, normal);
		if (criteria == 0) {
			return null;
		} 
		double t = dotProduct(subtract(origin, ray.x0), normal) / criteria;
		if (T_MIN <= t && t <= T_MAX) {
			Vec3 hitPoint = add(ray.x0, multiply(t, ray.d));
			return new Hit(t, hitPoint, normal, color);
		} 
		return null;
	}
}
