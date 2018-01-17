package curseSequences.a12.sceneObjects;
import static cgtools.Vec3.*;
import cgtools.Vec3;
import curseSequences.a12.materials.Material;
import curseSequences.a12.rayTracing.BoundingBox;
import curseSequences.a12.rayTracing.Hit;
import curseSequences.a12.rayTracing.Ray;

public class Plane implements Shape {

	public final Material material;
	public final Vec3 origin;
	public final Vec3 normal;
	public final double width;
	public final double depth;
	
	public Plane(Vec3 origin, Material material) {
		this(origin, material, 0, 0);
	}
	
	public Plane(Vec3 origin, Material material, double width, double depth) {
		this.origin = origin;
		this.normal = new Vec3(0, 1, 0);
		this.material = material;
		this.width = width;
		this.depth = depth;
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
			double xPos2U = (hitPoint.x - origin.x) / width + 0.5;
			double zPos2V = (hitPoint.z - origin.z) / depth + 0.5;
			return new Hit(t, hitPoint, normal, new Vec3(xPos2U, zPos2V, 0), material, toString());
		} 
		return null;
	}

	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(origin.x - width / 2, origin.y - 0.01, origin.z - depth / 2);
		Vec3 max = vec3(origin.x + width / 2, origin.y + 0.01, origin.z + depth / 2);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Plane [origin(Mitte)=" + origin + ", width=" + width + ", depth=" + depth + "]";
	}
}
