package curseSequences.a09.materials;

import static cgtools.Vec3.*;
import static cgtools.Vec3.zero;

import cgtools.Random;
import cgtools.Vec3;
import curseSequences.a09.rayTracing.Hit;
import curseSequences.a09.rayTracing.Ray;

public class GlassMaterial implements Material{
	
	public final Vec3 albedo;
	public final double glassRefractionIndex;
	public final double airRefractionIndex = 1;
	protected Ray scatteredRay;
	

	public GlassMaterial(Vec3 albedo, double glassRefractionIndex) {
		this.albedo = albedo;
		this.glassRefractionIndex = glassRefractionIndex;
	}

	@Override
	public Vec3 emittedRadiance(Ray r, Hit h) {
		return zero;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		double n1 = airRefractionIndex;
		double n2 = glassRefractionIndex;
		Vec3 hitPointNormal = normalize(h.normal);
		Vec3 rayDirection = normalize(r.d);
		double n_Dot_d = dotProduct(hitPointNormal, rayDirection);
		
		if (rayComesFromShapeInside(n_Dot_d)) {
			double temp = n1;
			n1 = n2;
			n2 = temp;
			hitPointNormal = multiply(-1, hitPointNormal);
		};
		
		double nRatio = n1 / n2;
		double c = -1 * dotProduct(hitPointNormal, rayDirection);
		double discriminant = 1 - nRatio * nRatio * (1 - c * c);
		
		if (discriminant < 0) {
			return reflect(n_Dot_d, hitPointNormal, rayDirection, h);
		} else {
			if (Random.random() > schlickCalc(n_Dot_d, n1, n2)) {
				return refract(hitPointNormal, rayDirection, nRatio, c, discriminant, h);
			} else {
				return reflect(n_Dot_d, hitPointNormal, rayDirection, h);
			}
		}	
	}
	
	private boolean rayComesFromShapeInside(double n_Dot_d) {
		return n_Dot_d > 0 ;
	}
	
	private double schlickCalc(double n_Dot_d, double n1, double n2) {
		double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
		return r0 + (1 - r0) * Math.pow((1 + n_Dot_d), 5);
	}
	
	private Ray refract(Vec3 hitPointNormal, Vec3 rayDirection, double nRatio, double c, double discriminant, Hit h) {
		Vec3 r_d = multiply(nRatio, rayDirection);
		Vec3 refractDirection = add(r_d, multiply(nRatio * c - Math.sqrt(discriminant), hitPointNormal));
		return new Ray(h.hitPoint, normalize(refractDirection));
	}
	
	private Ray reflect(double n_Dot_d, Vec3 hitPointNormal, Vec3 rayDirection, Hit h) {
		Vec3 reflexDirection = subtract(rayDirection, multiply(2 * n_Dot_d, hitPointNormal));
		return new Ray(h.hitPoint, normalize(reflexDirection));
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return albedo;
	}

	@Override
	public String toString() {
		return "RoughMetalMaterial [albedo=" + albedo + ", glassRefractionIndex=" + glassRefractionIndex + ", emitted=zero, lastScat"
				+ scatteredRay + "]";
	}
}
