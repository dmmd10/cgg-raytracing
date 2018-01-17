package curseSequences.a07.sceneObjects;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;
import curseSequences.a07.materials.Material;
import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.HitComparator;
import curseSequences.a07.rayTracing.Ray;

public class Cone implements Shape {

	public final Vec3 center;
	public final double height;
	public final Material material;
	protected final double yMin;
	protected final double yMax;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
	protected Disk disk;

	// Centerpoint ist obere Spitze
	public Cone(Vec3 center, double height, Material material) {
		this.center = vec3(center.x, center.y, center.z);
		this.height = height;
		this.material = material;
		yMin = center.y - height;
		yMax = center.y;
		disk = new Disk(vec3(center.x, center.y - height, center.z), vec3(0, -1, 0), height, material);
	}

	@Override
	public Hit intersect(Ray ray) {

		hitList = new ArrayList<Hit>();
		Hit coneHit = coneIntersect(ray);
		if (coneHit != null) {
			hitList.add(coneHit);
		}

		// Disks ausrechnen
		Hit diskHit = disk.intersect(ray);
		if (diskHit != null && ray.T_MIN <= diskHit.t && diskHit.t <= ray.T_MAX) {
			hitList.add(diskHit);
		}

		hitList.sort(hitComparator);
		if (!hitList.isEmpty()) {
			return hitList.get(0);
		}
		return null;
	}

	private Hit coneIntersect(Ray ray) {
		Vec3 hitPoint = null;
		Vec3 normal = null;

		Vec3 origin_minus_center = subtract(ray.x0, center);
		double a = Math.pow(ray.d.x, 2) + Math.pow(ray.d.z, 2) - Math.pow(ray.d.y, 2);
		double b = 2 * origin_minus_center.x * ray.d.x + 2 * origin_minus_center.z * ray.d.z
				- 2 * origin_minus_center.y * ray.d.y;
		double c = Math.pow(origin_minus_center.x, 2) + Math.pow(origin_minus_center.z, 2)
				- Math.pow(origin_minus_center.y, 2);
		double discriminante = b * b - 4 * a * c;

		if (discriminante < 0) {
			return null;
		}

		double t0 = (-b + Math.sqrt(discriminante)) / (2 * a);
		double t1 = (-b - Math.sqrt(discriminante)) / (2 * a);

		if (t0 > t1) {
			double tmp = t0;
			t0 = t1;
			t1 = tmp;
		}

		double y0 = ray.x0.y + t0 * ray.d.y;
		
		if (y0 <= yMax && y0 >= yMin) {
			if (t0 <= ray.T_MIN) {
				return null;
			}
			hitPoint = add(ray.x0, multiply(t0, ray.d));
			Vec3 XYnormalize = normalize(vec3(hitPoint.x - center.x, 0, hitPoint.z - center.z));
			normal = normalize(vec3(XYnormalize.x, 1, XYnormalize.z));
			return new Hit(t0, hitPoint, normal, material);
		} else {
			return null;
		}
	}

}
