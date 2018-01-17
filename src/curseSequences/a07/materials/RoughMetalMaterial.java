package curseSequences.a07.materials;

import cgtools.Vec3;
import static cgtools.Vec3.*;

import cgtools.Random;
import curseSequences.a07.rayTracing.Hit;
import curseSequences.a07.rayTracing.Ray;

public class RoughMetalMaterial implements Material {

	public final Vec3 albedo;
	public double roughness;
	protected Ray scatteredRay;

	// roughness liegt zwischen 0.0 und 1.0, für sinnvolle Resultate 0 bis 0.1
	public RoughMetalMaterial(Vec3 albedo, double roughness) {
		this.albedo = albedo;
		this.roughness = roughness;
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
		Vec3 reflexDirection = subtract(d, multiply(2 * n_Dot_d, n));
		Vec3 sphereRadiusVec = multiply(roughness, getRandomUnitCircleVector());
		Vec3 scatteredReflexDirection = add(normalize(reflexDirection), sphereRadiusVec);
		if (dotProduct(n, scatteredReflexDirection) >= 0) {
			scatteredRay = new Ray(h.hitPoint, normalize(scatteredReflexDirection));
			return scatteredRay;
		}
		return null;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return albedo;
	}

	@Override
	public String toString() {
		return "RoughMetalMaterial [albedo=" + albedo + ", roughness=" + roughness + ", emitted=zero, lastScat"
				+ scatteredRay + "]";
	}
}
