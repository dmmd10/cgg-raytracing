package curseSequences.a09.materials;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public class MetalMaterial implements Material{

	public final Vec3 albedo;
	protected Ray scatteredRay;
	
	public MetalMaterial(Vec3 albedo) {
		this.albedo = albedo;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return zero;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		Vec3 d = normalize(r.d);
		Vec3 n = normalize(h.normal);
		double n_Dot_d = dotProduct(n, d);
		Vec3 reflexDirection = subtract(d,multiply(2 * n_Dot_d, n));
		return new Ray(h.hitPoint, normalize(reflexDirection));
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return albedo;
	}

	@Override
	public String toString() {
		return "MetalMaterial [albedo=" + albedo + ", emitted=zero, lastScat" + scatteredRay + "]";
	}
}
