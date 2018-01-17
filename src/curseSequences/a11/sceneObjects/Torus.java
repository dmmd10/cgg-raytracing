package curseSequences.a11.sceneObjects;

import static cgtools.Vec3.*;

import java.util.Arrays;

import cgtools.Vec3;
import curseSequences.a11.rayTracing.BoundingBox;
import curseSequences.a11.rayTracing.Hit;
import curseSequences.a11.rayTracing.Ray;
import curseSequences.a11.materials.Material;
import static curseSequences.a11.maths.EquationSolver.*;

public class Torus implements Shape {

	private double[] tArray;
	public final double R;
	public final double rTunel;
	public final Vec3 center;
	public final Material material;

	public Torus(Vec3 center, double R, double rTunel, Material material) {
		this.center = center;
		this.R = R;
		this.rTunel = rTunel;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray ray) {
		
		// test cylinder intersect first (optimization)
		Vec3 origin_minus_center = subtract(ray.x0, center);
		double a = Math.pow(ray.d.x, 2) + Math.pow(ray.d.z, 2);
		double b = 2 * origin_minus_center.x * ray.d.x + 2 * origin_minus_center.z * ray.d.z;
		double c = Math.pow(origin_minus_center.x, 2) + Math.pow(origin_minus_center.z, 2) - Math.pow(R + rTunel, 2);
		double discriminante = b * b - 4 * a * c;

		if (discriminante < 0) {
			return null;
		}
		
		double t0 = (-b + Math.sqrt(discriminante)) / (2 * a);
		double t1 = (-b - Math.sqrt(discriminante)) / (2 * a);
		double p0 = ray.x0.y + t0 * ray.d.y;
		double p1 = ray.x0.y + t1 * ray.d.y;
		
		if ((p0 > (center.y + rTunel) && p1 > (center.y + rTunel)) 
				|| (p0 < (center.y - rTunel) && p1 < (center.y - rTunel))) {
			return null;
		}
		
		// start torus intersect
		int numSolutions = solveIntersections(ray);
        Arrays.sort(tArray, 0, numSolutions);
        
        for (int i=0; i < numSolutions; ++i)
        {
        	if (ray.contains(tArray[i])) { // geändert hier!
        		Vec3 hitPoint = add(ray.x0, multiply(tArray[i],ray.d));
        		// Texturkoordinaten u,v fehlen noch!!!
        		return new Hit(tArray[i], hitPoint, surfaceNormal(hitPoint), null, material, toString());
        	}
        }
        return null;
	}

	public int solveIntersections(Ray ray) {

		Vec3 origin_minus_center = subtract(ray.x0, center);
		double T = 4.0 * R * R;
		double G = T * (ray.d.x * ray.d.x + ray.d.z * ray.d.z);
		double H = 2.0 * T * (origin_minus_center.x * ray.d.x + origin_minus_center.z * ray.d.z);
		double I = T * (origin_minus_center.x * origin_minus_center.x + origin_minus_center.z * origin_minus_center.z);
		double J = magnitudeSquared(ray.d);
		double K = 2.0 * dotProduct(origin_minus_center, ray.d);
		double L = magnitudeSquared(origin_minus_center) + R * R - rTunel * rTunel;

		tArray = solveQuartic(J * J, // coefficient of u^4
				2.0 * J * K, // coefficient of u^3
				2.0 * J * L + K * K - G, // coefficient of u^2
				2.0 * K * L - H, // coefficient of u^1 = u
				L * L - I // coefficient of u^0 = constant term
		);
		int numRealRoots = tArray.length;
		double SURFACE_TOLERANCE = ray.T_MIN;
		int numPositiveRoots = 0;
		for (int i = 0; i < numRealRoots; ++i) {
			if (tArray[i] > SURFACE_TOLERANCE) {
				tArray[numPositiveRoots++] = tArray[i];
			}
		}
		return numPositiveRoots;
	}
	
	private Vec3 surfaceNormal(Vec3 hitPoint) {
//		Vec3 torusNormal = vec3(0, 1, 0);
//		double k = dotProduct(subtract(hitPoint, center), torusNormal);
//		Vec3 A = subtract(hitPoint, multiply(k, torusNormal));
//		double m = Math.sqrt(rTunel * rTunel - k * k);
//		return normalize(subtract(subtract(hitPoint, A), multiply(m / (R + m), subtract(center, A))));
		
//		Vec3 torusNormal = vec3(0, 1, 0); // für metal okay
//		double k = dotProduct(subtract(hitPoint, center), torusNormal);
//		Vec3 D = subtract(subtract(hitPoint, center), multiply(k, torusNormal));
//		Vec3 X = multiply(R, multiply(1/Math.sqrt(dotProduct(D, D)), D));
//		return subtract(X, subtract(hitPoint, center));
		
		double a = 1.0 - (R / Math.sqrt(hitPoint.x * hitPoint.x + hitPoint.z * hitPoint.z));
		return normalize(vec3(a * hitPoint.x, hitPoint.y, a * hitPoint.z));
	}
	
	@Override
	public BoundingBox bounding() {
		Vec3 min = vec3(center.x - R - rTunel, center.y - rTunel, center.z - R - rTunel);
		Vec3 max = vec3(center.x + R + rTunel, center.y + rTunel, center.z + R + rTunel);
		return new BoundingBox(min, max);
	}

	@Override
	public String toString() {
		return "Torus [R=" + R + ", rTunel=" + rTunel + ", center=" + center + "]";
	}
}
