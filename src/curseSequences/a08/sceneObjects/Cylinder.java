package curseSequences.a08.sceneObjects;

import static cgtools.Vec3.*;

import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;
import curseSequences.a08.materials.Material;
import curseSequences.a08.rayTracing.Hit;
import curseSequences.a08.rayTracing.HitComparator;
import curseSequences.a08.rayTracing.Ray;

public class Cylinder implements Shape {

	public final Vec3 center;
	public final double radius;
	public final double height;
	public final Material material;
	protected final double yMin;
	protected final double yMax;
	protected List<Hit> hitList;
	protected final HitComparator hitComparator = new HitComparator();
	protected Disk upperDisk;
	protected Disk bottomDisk;

	// Centerpoint ist unten im Zentrum
	public Cylinder(Vec3 center, double radius, double height, Material material) {
		this.center = center;
		this.radius = radius;
		this.height = height;
		this.material = material;
		yMin = center.y;
		yMax = center.y + height;
		upperDisk = new Disk(vec3(center.x, center.y + height, center.z), vec3(0, 1, 0), radius, material);
		bottomDisk = new Disk(center, vec3(0, 1, 0), radius, material);
	}

	@Override
	public Hit intersect(Ray ray) {

		hitList = new ArrayList<Hit>();
		Hit cylinderHit = cylinderIntersect(ray);
		if (cylinderHit != null) {
			hitList.add(cylinderHit);
		}
		
		// Disks ausrechnen
		Hit upperDiskHit = upperDisk.intersect(ray);
		Hit bottomDiskHit = bottomDisk.intersect(ray);
		if (upperDiskHit != null && ray.T_MIN <= upperDiskHit.t && upperDiskHit.t <= ray.T_MAX) {
			hitList.add(upperDiskHit);
		}
		if (bottomDiskHit != null && ray.T_MIN <= bottomDiskHit.t && bottomDiskHit.t <= ray.T_MAX) {
			hitList.add(bottomDiskHit);
		}
		
		hitList.sort(hitComparator);
		if (!hitList.isEmpty()){
			return hitList.get(0);
		}
		return null;
	}
	
	private Hit cylinderIntersect(Ray ray) {
		
		Vec3 hitPoint = null;
		Vec3 normal = null;
		
		Vec3 origin_minus_center = subtract(ray.x0, center);
		double a = Math.pow(ray.d.x, 2) + Math.pow(ray.d.z, 2);
		double b = 2 * origin_minus_center.x * ray.d.x + 2 * origin_minus_center.z * ray.d.z;
		double c = Math.pow(origin_minus_center.x, 2) + Math.pow(origin_minus_center.z, 2) - Math.pow(radius, 2);
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
			normal = normalize(vec3(hitPoint.x - center.x, 0, hitPoint.z - center.z));
			return new Hit(t0, hitPoint, normal, material);
		} else {
			return null;
		}
	}

}
